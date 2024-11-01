package kh.farrukh.sortmapper.config.model

/**
 * Enum class to specify the work mode of the SortMapper.
 */
enum class WorkMode {
    /**
     * ENABLED mode means that all features are enabled.
      */
    ENABLED,

    /**
     * MAPPER_DISABLED mode means that only sort argument resolving is enabled.
     * In other words, beans will be created and annotations will be processed.
     * But the actual mapping will not be performed.
     *
     * This mode is useful if you want to enable SortMapper again but without restarting the application.
     * For this, you should use Spring Actuator to re-read the configuration using the refresh endpoint.
     */
    MAPPER_DISABLED,

    /**
     * FULLY_DISABLED mode means that all features are disabled.
     * Beans will not be created and annotations will not be processed.
     *
     * After changing the mode to another one, you should restart the application to apply the changes.
     */
    FULLY_DISABLED
}