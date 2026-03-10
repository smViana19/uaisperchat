package br.com.smvtech.uaisperchat.presentation.exception;

public class NotFoundException extends RuntimeException {
  public NotFoundException(String message) {
    super(message);
  }
}
