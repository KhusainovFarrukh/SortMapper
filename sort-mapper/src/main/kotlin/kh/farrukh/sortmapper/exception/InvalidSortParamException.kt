package kh.farrukh.sortmapper.exception

/**
 * Exception to be thrown when an invalid sort parameter is provided.
 */
class InvalidSortParamException(
    val param: String,
    val validParams: List<String>
) : RuntimeException(
    "Invalid sort parameter: $param. Valid parameters are: ${validParams.joinToString(", ")}"
) {
}