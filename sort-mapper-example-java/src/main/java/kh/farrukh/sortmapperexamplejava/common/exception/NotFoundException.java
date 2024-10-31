package kh.farrukh.sortmapperexamplejava.common.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends BaseException {

  public NotFoundException(String message) {
    this(message, null);
  }

  public NotFoundException(String message, Throwable cause) {
    super(message, HttpStatus.NOT_FOUND , cause);
  }

}
