package kh.farrukh.sortmapper.bpp

import kh.farrukh.sortmapper.annotation.SortField
import kh.farrukh.sortmapper.annotation.SortMapping
import kh.farrukh.sortmapper.model.ParamMapping
import java.lang.reflect.Method

fun getParamMappings(
    sortMapping: SortMapping,
    method: Method
): List<ParamMapping> {
    val paramMappings = mutableSetOf<ParamMapping>()

    val paramMappingsFromProvider = sortMapping.getParamMappingsFromProvider()
    val paramMappingsFromReturnType = method.returnType.getParamMappingsFromReturnType()

    paramMappings.addAll(paramMappingsFromProvider)
    paramMappings.addAll(paramMappingsFromReturnType)

    return paramMappings.toList()
}

fun SortMapping.getParamMappingsFromProvider(): List<ParamMapping> {
    val providerClass = this.provider.java
    val paramMappingProvider = providerClass.getDeclaredConstructor().newInstance()
    return paramMappingProvider.getParamMappings()
}

fun Class<*>.getParamMappingsFromReturnType(): List<ParamMapping> {
    val paramMappings = mutableSetOf<ParamMapping>()

    paramMappings.addAll(getParamMappings())

    var superClass = superclass
    while (superClass != null) {
        paramMappings.addAll(superClass.getParamMappings())
        superClass = superClass.superclass
    }

    return paramMappings.toList()
}

fun Class<*>.getParamMappings(): List<ParamMapping> {
    return declaredFields
        .filter { it.isAnnotationPresent(SortField::class.java) }
        .map { field ->
            val sortField = field.getAnnotation(SortField::class.java)

            //todo: handle hierarchical fields (e.g. consumerStatus.nameUz)
            val apiParam = field.name
            val entityProperty = sortField.value

            ParamMapping(apiParam, entityProperty)
        }
}

fun Method.getKeyMethod(targetClass: Class<*>, beanClass: Class<*>): Method {
    if (targetClass == beanClass) return this
    return beanClass.getMethod(name, *parameterTypes)
}