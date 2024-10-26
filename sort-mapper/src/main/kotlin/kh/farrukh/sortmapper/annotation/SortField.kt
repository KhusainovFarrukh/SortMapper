package kh.farrukh.sortmapper.annotation

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class SortField(

    val value: String

)
