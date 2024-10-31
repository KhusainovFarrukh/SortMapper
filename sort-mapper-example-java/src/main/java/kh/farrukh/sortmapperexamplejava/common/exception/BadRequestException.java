package kh.farrukh.sortmapperexamplejava.common.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends BaseException {

  public BadRequestException(String message) {
    this(message, null);
  }

  public BadRequestException(String message, Throwable cause) {
    super(message, HttpStatus.BAD_REQUEST, cause);
  }

}
