package kh.farrukh.sortmapper.exception

import kh.farrukh.sortmapper.config.model.SortMapperConfigProperties
import kh.farrukh.sortmapper.exception.model.ErrorResponse
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.LocalDateTime

/**
 * Exception handler for handling [InvalidSortParamException].
 */
@Component
@ConditionalOnProperty(
    "sort-mapper.exception.handler-enabled",
    havingValue = "true",
    matchIfMissing = true
)
@ControllerAdvice
class SortMapperExceptionHandler(
    private val sortMapperConfigProperties: SortMapperConfigProperties
) {

    @ExceptionHandler(InvalidSortParamException::class)
    fun handleInvalidSortParamException(ex: InvalidSortParamException): ResponseEntity<ErrorResponse> {
        val httpStatus = HttpStatus
            .resolve(sortMapperConfigProperties.exception.httpCode)
            ?: DEFAULT_HTTP_STATUS

        val stacktrace = if (sortMapperConfigProperties.exception.stacktraceEnabled) {
            ex.stackTrace.joinToString(" ")
        } else {
            DEFAULT_STACKTRACE
        }

        return ResponseEntity
            .status(httpStatus)
            .body(
                ErrorResponse(
                    ex.message ?: DEFAULT_ERROR_MESSAGE, LocalDateTime.now(), stacktrace
                )
            )
    }

    companion object {
        val DEFAULT_HTTP_STATUS = HttpStatus.BAD_REQUEST
        val DEFAULT_STACKTRACE: String? = null
        const val DEFAULT_ERROR_MESSAGE = "Invalid sort parameter"
    }

}