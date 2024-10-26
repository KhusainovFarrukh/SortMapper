package kh.farrukh.sortmapper.bpp

import kh.farrukh.sortmapper.annotation.SortField
import kh.farrukh.sortmapper.annotation.SortMapping
import kh.farrukh.sortmapper.model.ParamMapping
import java.lang.reflect.Method
import java.lang.reflect.ParameterizedType

fun getParamMappings(
    sortMapping: SortMapping,
    method: Method
): List<ParamMapping> {
    val paramMappings = mutableSetOf<ParamMapping>()

    val paramMappingsFromProvider = sortMapping.getParamMappingsFromProvider()
    val paramMappingsFromReturnType = method.getActualReturnType().getParamMappingsFromReturnType()

    paramMappings.addAll(paramMappingsFromProvider)
    paramMappings.addAll(paramMappingsFromReturnType)

    return paramMappings.toList()
}

fun Method.getActualReturnType(): Class<*> {
    if (genericReturnType !is ParameterizedType) return returnType

    return (genericReturnType as ParameterizedType).getActualType()
}

fun ParameterizedType.getActualType(): Class<*> {
    if (actualTypeArguments.isEmpty()) throw IllegalArgumentException("No actual type arguments found")
    if (actualTypeArguments.size > 1) throw IllegalArgumentException("More than one actual type argument found")

    val actualType = actualTypeArguments[0]

    if (actualType is ParameterizedType) return actualType.getActualType()
    if (actualType is Class<*>) return actualType

    throw IllegalArgumentException("Unknown actual type argument type: ${actualType::class.java}")
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