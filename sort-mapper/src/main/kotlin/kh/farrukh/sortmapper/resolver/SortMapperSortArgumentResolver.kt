package kh.farrukh.sortmapper.resolver

import kh.farrukh.sortmapper.config.model.SortMapperConfigProperties
import kh.farrukh.sortmapper.config.model.ParamValidationType
import kh.farrukh.sortmapper.config.model.WorkMode
import kh.farrukh.sortmapper.exception.InvalidSortParamException
import kh.farrukh.sortmapper.model.ParamMapping
import kh.farrukh.sortmapper.provider.entityfield.EntityFieldProvider
import kh.farrukh.sortmapper.provider.sortmapping.SortMappingProvider
import org.slf4j.LoggerFactory
import org.springframework.core.MethodParameter
import org.springframework.data.domain.Sort
import org.springframework.data.web.SortArgumentResolver
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.ModelAndViewContainer

/**
 * Custom implementation of Spring's [SortArgumentResolver] which maps the sort parameters
 * according to the specified mappings.
 * Delegates to the original [SortArgumentResolver] to resolve the original sort.
 * Then simply maps the property and direction will remain the same.
 */
class SortMapperSortArgumentResolver(
    private val delegate: SortArgumentResolver,
    private val sortMappingProvider: SortMappingProvider,
    private val entityFieldProvider: EntityFieldProvider,
    private val sortMapperConfigProperties: SortMapperConfigProperties
) : SortArgumentResolver {

    /**
     * Delegates to the original [SortArgumentResolver].
     */
    override fun supportsParameter(parameter: MethodParameter) =
        delegate.supportsParameter(parameter)

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Sort {
        log.trace("Resolving sort argument: parameter = {}, webRequest = {}", parameter, webRequest)
        val originalSort =
            delegate.resolveArgument(parameter, mavContainer, webRequest, binderFactory)

        if (sortMapperConfigProperties.workMode != WorkMode.ENABLED) {
            return originalSort
        }

        val mappingValue = parameter.method
            ?.let(sortMappingProvider::getMapping)
            ?: return originalSort

        //TODO by farrukh_kh on 01 Nov 2024: Think about using some kind of cache for entityFields,
        // because it is not efficient to calculate them every time using reflection
        //TODO by farrukh_kh on 01 Nov 2024: entityField must handle courseTeacherFirstName for course.teacher.firstName cases
        val entityFields = entityFieldProvider.getEntityFields(mappingValue.entityClass).toSet()

        val paramMappings = mappingValue.paramMappings
        val specifiedFields = paramMappings.map(ParamMapping::apiParam)

        val paramMappingsMap = paramMappings
            .associateBy(ParamMapping::apiParam, ParamMapping::sortField)

        val result = mapSortParams(originalSort, entityFields, specifiedFields, paramMappingsMap)

        log.trace(
            "Mapped sort params: parameter = {}, webRequest = {}, result = {}",
            parameter,
            webRequest,
            result
        )
        return result;
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

            val validSortParams = when (sortMapperConfigProperties.paramValidationType) {
                //only specified fields are allowed
                ParamValidationType.STRICT -> specifiedFields
                //both specified and entity fields are allowed
                ParamValidationType.SOFT -> specifiedFields + entityFields
                ParamValidationType.NONE -> listOf(property)
            }

            if (!validSortParams.contains(property)) {
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

    companion object {
        private val log = LoggerFactory.getLogger(this::class.java)
    }

}