package kh.farrukh.sortmapper.annotation

/**
 * Annotation to specify actual field name for sorting.
 */
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class SortField(

    /**
     * The actual field name for sorting.
     */
    val value: String

)
