package kh.farrukh.sortmapperexample.common.exception

import org.springframework.http.HttpStatus
import java.time.LocalDateTime

abstract class BaseException(
    override val message: String,
    val httpStatus: HttpStatus,
    override val cause: Throwable? = null,
    val time: LocalDateTime = LocalDateTime.now()
) : RuntimeException(
    message, cause
)