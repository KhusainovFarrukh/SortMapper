package kh.farrukh.config.model

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