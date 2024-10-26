package kh.farrukh.model

data class MappingValue(
    val entityClass: Class<*>,
    val paramMappings: List<ParamMapping>
)
