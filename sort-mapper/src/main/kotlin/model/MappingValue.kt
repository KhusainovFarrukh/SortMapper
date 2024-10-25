package kh.farrukh.model

import kotlin.reflect.KClass

data class MappingValue(
    val entityClass: KClass<*>,
    val paramMappings: List<ParamMapping>
)
