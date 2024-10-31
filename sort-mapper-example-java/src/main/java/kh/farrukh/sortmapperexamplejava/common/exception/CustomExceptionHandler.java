package kh.farrukh.sortmapperexamplejava.common.exception;

import java.time.LocalDateTime;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@Component
@ControllerAdvice
public class CustomExceptionHandler {

  @ExceptionHandler(BaseException.class)
  public ResponseEntity<ErrorResponse> handleBaseException(BaseException e) {
    log.info("BaseException: ", e);
    return ResponseEntity
        .status(e.getHttpStatus())
        .body(new ErrorResponse(
            e.getMessage(), e.getHttpStatus(), Arrays.toString(e.getStackTrace()), e.getTime()
        ));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleUnknownException(Exception e) {
    log.error("UnknownException: ", e);
    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(new ErrorResponse(
            e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, Arrays.toString(e.getStackTrace()),
            LocalDateTime.now()
        ));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException e
  ) {
    log.info("MethodArgumentNotValidException: ", e);
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(new ErrorResponse(
            e.getMessage(), HttpStatus.BAD_REQUEST, Arrays.toString(e.getStackTrace()),
            LocalDateTime.now()
        ));
  }

}
