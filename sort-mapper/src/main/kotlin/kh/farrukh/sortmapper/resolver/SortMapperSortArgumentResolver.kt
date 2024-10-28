package kh.farrukh.sortmapper.resolver

import kh.farrukh.sortmapper.config.model.SortMapperConfigProperties
import kh.farrukh.sortmapper.config.model.ParamValidationType
import kh.farrukh.sortmapper.config.model.WorkMode
import kh.farrukh.sortmapper.exception.InvalidSortParamException
import kh.farrukh.sortmapper.model.ParamMapping
import kh.farrukh.sortmapper.provider.entityfield.EntityFieldProvider
import kh.farrukh.sortmapper.provider.sortmapping.SortMappingProvider
import org.springframework.core.MethodParameter
import org.springframework.data.domain.Sort
import org.springframework.data.web.SortArgumentResolver
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.ModelAndViewContainer

class SortMapperSortArgumentResolver(
    private val delegate: SortArgumentResolver,
    private val sortMappingProvider: SortMappingProvider,
    private val entityFieldProvider: EntityFieldProvider,
    private val sortMapperConfigProperties: SortMapperConfigProperties
) : SortArgumentResolver {

    override fun supportsParameter(parameter: MethodParameter) =
        delegate.supportsParameter(parameter)

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Sort {
        val originalSort =
            delegate.resolveArgument(parameter, mavContainer, webRequest, binderFactory)

        if (sortMapperConfigProperties.workMode != WorkMode.ENABLED) {
            return originalSort
        }

        val mappingValue = parameter.method
            ?.let(sortMappingProvider::getMapping)
            ?: return originalSort

        //todo: entityFields can be cached
        //todo: entityFields now must handle courseTeacherFirstName for course.teacher.firstName cases
        val entityFields = entityFieldProvider.getEntityFields(mappingValue.entityClass).toSet()

        val paramMappings = mappingValue.paramMappings
        val specifiedFields = paramMappings.map(ParamMapping::apiParam)

        val paramMappingsMap = paramMappings
            .associateBy(ParamMapping::apiParam, ParamMapping::sortField)

        return mapSortParams(originalSort, entityFields, specifiedFields, paramMappingsMap)
    }

    private fun mapSortParams(
        originalSort: Sort,
        entityFields: Set<String>,
        specifiedFields: List<String>,
        paramMappingsMap: Map<String, String>
    ): Sort {
        var mappedSort = Sort.unsorted()

        for (order in originalSort) {
            val property = order.property

            //todo: move validation part to another bean (make configurable by implementing interface)
            val validSortParams = when (sortMapperConfigProperties.paramValidationType) {
                //only specified fields are allowed
                ParamValidationType.STRICT -> specifiedFields
                //both specified and entity fields are allowed
                ParamValidationType.SOFT -> specifiedFields + entityFields
                ParamValidationType.NONE -> listOf(property)
            }

            if (!validSortParams.contains(property)) {
                //todo: custom exception
                throw InvalidSortParamException(property, validSortParams)
            }

            val extractedSort = extractSort(order, paramMappingsMap)
            mappedSort = mappedSort.and(extractedSort)
        }

        return mappedSort
    }

    private fun extractSort(
        order: Sort.Order,
        paramMappingsMap: Map<String, String>
    ): Sort {
        val property = order.property
        val direction = order.direction

        val sortField = paramMappingsMap[property] ?: property
        return Sort.by(Sort.Order(direction, sortField))
    }

}