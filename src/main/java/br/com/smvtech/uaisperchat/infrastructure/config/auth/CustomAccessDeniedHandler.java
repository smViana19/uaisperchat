package br.com.smvtech.uaisperchat.infrastructure.config.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {
  private final ObjectMapper objectMapper;

  public CustomAccessDeniedHandler(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
    response.setStatus(HttpStatus.FORBIDDEN.value());
    response.setContentType("application/json");

    Map<String, Object> body = new HashMap<>();
    body.put("error", HttpStatus.FORBIDDEN.getReasonPhrase());
    body.put("status", HttpStatus.FORBIDDEN.value());
    body.put("message", "Você não tem permissão para acessar este recurso");

    String responseBody = objectMapper.writeValueAsString(body);
    response.getWriter().write(responseBody);
  }
}
