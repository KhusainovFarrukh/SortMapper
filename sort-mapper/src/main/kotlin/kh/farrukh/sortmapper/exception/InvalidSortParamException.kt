package kh.farrukh.sortmapper.exception

//todo: maybe add exception handler (ControllerAdvice)
class InvalidSortParamException(
    val param: String,
    val validParams: List<String>
) : RuntimeException(
    "Invalid sort parameter: $param. Valid parameters are: ${validParams.joinToString(", ")}"
) {
}