package br.com.smvtech.uaisperchat.presentation.exception.handler;

import br.com.smvtech.uaisperchat.presentation.exception.ConflictException;
import br.com.smvtech.uaisperchat.presentation.exception.ForbiddenException;
import br.com.smvtech.uaisperchat.presentation.exception.InvalidRequestException;
import br.com.smvtech.uaisperchat.presentation.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<Map<String, Object>> handleNotFoundException(NotFoundException ex) {
    Map<String, Object> body = new HashMap<>();
    body.put("error", HttpStatus.NOT_FOUND.getReasonPhrase());
    body.put("status", HttpStatus.NOT_FOUND.value());
    body.put("message", ex.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
  }

  @ExceptionHandler(InvalidRequestException.class)
  public ResponseEntity<Map<String, Object>> handleBadRequestException(InvalidRequestException ex) {
    Map<String, Object> body = new HashMap<>();
    body.put("error", HttpStatus.BAD_REQUEST.getReasonPhrase());
    body.put("status", HttpStatus.BAD_REQUEST.value());
    body.put("message", ex.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
  }

  @ExceptionHandler(ConflictException.class)
  public ResponseEntity<Map<String, Object>> handleConflictException(ConflictException ex) {
    Map<String, Object> body = new HashMap<>();
    body.put("error", HttpStatus.CONFLICT.getReasonPhrase());
    body.put("status", HttpStatus.CONFLICT.value());
    body.put("message", ex.getMessage());
    return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
  }

  @ExceptionHandler(ForbiddenException.class)
  public ResponseEntity<Map<String, Object>> handleForbbidenException(ForbiddenException ex) {
    Map<String, Object> body = new HashMap<>();
    body.put("error", HttpStatus.FORBIDDEN.getReasonPhrase());
    body.put("status", HttpStatus.FORBIDDEN.value());
    body.put("message", ex.getMessage());
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(body);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex) {
    Map<String, Object> body = new HashMap<>();
    body.put("error", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
    body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
    body.put("message", ex.getMessage());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> handleValidationExceptions(
      MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getFieldErrors().forEach(error -> {
      errors.put(error.getField(), error.getDefaultMessage());
    });

    return ResponseEntity.badRequest().body(errors);
  }
}
