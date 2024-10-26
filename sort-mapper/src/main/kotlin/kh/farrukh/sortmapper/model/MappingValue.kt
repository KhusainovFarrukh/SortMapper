package kh.farrukh.kh.farrukh.sortmapper.model

data class MappingValue(
    val entityClass: Class<*>,
    val paramMappings: List<ParamMapping>
)
