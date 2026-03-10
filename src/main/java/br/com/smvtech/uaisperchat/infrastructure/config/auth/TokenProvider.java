package br.com.smvtech.uaisperchat.infrastructure.config.auth;

import java.util.Map;

public interface TokenProvider {
  String generateToken(String subject, Map<String, Object> claims);

  boolean isValid(String token);

  String getSubject(String token);
}
