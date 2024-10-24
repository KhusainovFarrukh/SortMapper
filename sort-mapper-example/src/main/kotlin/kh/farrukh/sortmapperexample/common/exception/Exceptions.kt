package kh.farrukh.sortmapperexample.common.exception

import org.springframework.http.HttpStatus

class NotFoundException(
    message: String,
    cause: Throwable? = null
) : BaseException(message, HttpStatus.NOT_FOUND, cause)

class BadRequestException(
    message: String,
    cause: Throwable? = null
) : BaseException(message, HttpStatus.BAD_REQUEST, cause)