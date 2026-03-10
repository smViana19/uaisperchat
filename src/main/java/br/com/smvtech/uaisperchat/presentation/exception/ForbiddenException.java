package br.com.smvtech.uaisperchat.presentation.exception;

public class ForbiddenException extends RuntimeException {
  public ForbiddenException(String message) {
    super(message);
  }
  public ForbiddenException(String message, Throwable cause) {
    super(message, cause);
  }
}
