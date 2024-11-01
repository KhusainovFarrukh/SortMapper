package kh.farrukh.sortmapper.config.model

/**
 * Enum class to specify the type of validation to be done on sort parameters.
 */
enum class ParamValidationType {
    /**
     * Allows only specified sort parameters (via @SortMapping annotation)
     */
    STRICT,

    /**
     * Allows specified sort parameters (via @SortMapping and entity properties
     */
    SOFT,

    /**
     * Allows any sort parameters
     */
    NONE

}