package kh.farrukh.sortmapper.model

/**
 * Data class to hold the actual mappings (entity class and specified param mappings).
 */
data class MappingValue(
    val entityClass: Class<*>,
    val paramMappings: List<ParamMapping>
)
