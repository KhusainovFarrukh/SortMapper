package kh.farrukh.sortmapper.exception.model

import java.time.LocalDateTime

/**
 * Error response model to be returned in case of a sort mapper exception.
 */
data class ErrorResponse(
    val message: String,
    val time: LocalDateTime,
    val stacktrace: String?
)
