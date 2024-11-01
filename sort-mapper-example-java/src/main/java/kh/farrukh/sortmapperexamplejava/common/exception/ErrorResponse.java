package kh.farrukh.sortmapperexamplejava.common.exception;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;

public record ErrorResponse(
    String message,
    HttpStatus httpStatus,
    String stacktrace,
    LocalDateTime timestamp
) {

}
