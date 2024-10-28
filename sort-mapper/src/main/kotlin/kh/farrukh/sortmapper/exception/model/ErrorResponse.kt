package kh.farrukh.sortmapper.exception.model

import java.time.LocalDateTime

data class ErrorResponse(
    val message: String,
    val time: LocalDateTime,
    val stacktrace: String?
)
