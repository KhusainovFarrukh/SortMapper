package kh.farrukh.sortmapperexample.common.exception

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.LocalDateTime

@ControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(BaseException::class)
    fun handleBaseException(exception: BaseException): ResponseEntity<ErrorResponse> {
        log.info("BaseException", exception)
        return ResponseEntity
            .status(exception.httpStatus)
            .body(
                ErrorResponse(
                    message = exception.message,
                    httpStatus = exception.httpStatus,
                    stacktrace = exception.stackTraceToString(),
                    timestamp = exception.time
                )
            )
    }

    @ExceptionHandler(Exception::class)
    fun handleUnknownException(exception: Exception): ResponseEntity<ErrorResponse> {
        log.error("Unknown exception", exception)
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(
                ErrorResponse(
                    message = exception.message ?: "Unknown error",
                    httpStatus = HttpStatus.INTERNAL_SERVER_ERROR,
                    stacktrace = exception.stackTraceToString(),
                    timestamp = LocalDateTime.now()
                )
            )
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(exception: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {
        log.info("MethodArgumentNotValidException", exception)
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(
                ErrorResponse(
                    message = exception.bindingResult.fieldErrors.joinToString { "${it.field}: ${it.defaultMessage}" },
                    httpStatus = HttpStatus.BAD_REQUEST,
                    stacktrace = exception.stackTraceToString(),
                    timestamp = LocalDateTime.now()
                )
            )
    }

    companion object {
        private val log = LoggerFactory.getLogger(this::class.java)
    }

}