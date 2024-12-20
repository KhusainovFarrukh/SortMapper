package kh.farrukh.sortmapper.config.model

import org.springframework.http.HttpStatus

/**
 * Configuration properties for exception handling.
 */
class ExceptionConfigProperties {
    var handlerEnabled: Boolean = true
    var httpCode: Int = HttpStatus.BAD_REQUEST.value()
    var stacktraceEnabled: Boolean = false
}