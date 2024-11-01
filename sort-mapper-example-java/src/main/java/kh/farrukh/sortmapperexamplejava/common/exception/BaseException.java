package kh.farrukh.sortmapperexamplejava.common.exception;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public abstract class BaseException extends RuntimeException {

  private String message;
  private HttpStatus httpStatus;
  private LocalDateTime time;

  public BaseException(String message, HttpStatus httpStatus, Throwable cause) {
    super(message, cause);
    this.message = message;
    this.httpStatus = httpStatus;
    this.time = LocalDateTime.now();
  }
}
