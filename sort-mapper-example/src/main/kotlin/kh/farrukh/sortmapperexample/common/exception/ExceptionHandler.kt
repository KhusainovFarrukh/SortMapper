package kh.farrukh.sortmapperexample.common.exception

import com.fasterxml.jackson.databind.exc.MismatchedInputException
import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
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

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleHttpMessageNotReadableException(exception: HttpMessageNotReadableException): ResponseEntity<ErrorResponse> {
        log.info("HttpMessageNotReadableException", exception)
        return exception.cause?.let { cause ->
            when (cause) {
                is MissingKotlinParameterException -> handleMissingKotlinParameterException(cause)
                is MismatchedInputException -> handleMismatchedInputException(cause)
                else -> handleUnknownException(exception)
            }
        } ?: handleUnknownException(exception)
    }

    @ExceptionHandler(MissingKotlinParameterException::class)
    fun handleMissingKotlinParameterException(exception: MissingKotlinParameterException): ResponseEntity<ErrorResponse> {
        log.info("MissingKotlinParameterException", exception)
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(
                ErrorResponse(
                    message = "${exception.parameter.name ?: "Parameter"} is required",
                    httpStatus = HttpStatus.BAD_REQUEST,
                    stacktrace = exception.stackTraceToString(),
                    timestamp = LocalDateTime.now()
                )
            )
    }

    @ExceptionHandler(MismatchedInputException::class)
    fun handleMismatchedInputException(exception: MismatchedInputException): ResponseEntity<ErrorResponse> {
        log.info("MismatchedInputException", exception)
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(
                ErrorResponse(
                    message = exception.message ?: "Invalid input",
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