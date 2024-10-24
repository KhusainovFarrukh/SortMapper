package kh.farrukh.sortmapperexample.common.exception

import org.springframework.http.HttpStatus
import java.time.LocalDateTime

data class ErrorResponse(
    val message: String,
    val httpStatus: HttpStatus,
    val stacktrace: String,
    val timestamp: LocalDateTime
)
