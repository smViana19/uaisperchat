package br.com.smvtech.uaisperchat.infrastructure.config.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

@Component
public class JwtProvider implements TokenProvider {

  private final SecretKey secretKey;
  private final long expirationMillis;


  public JwtProvider(@Value("${security.jwt.secret}") String secret,
                     @Value("${security.jwt.expiration}") long expirationMillis) {
    this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    this.expirationMillis = expirationMillis;
  }

  @Override
  public String generateToken(String subject, Map<String, Object> claims) {
    return Jwts.builder()
        .setSubject(subject).addClaims(claims)
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + expirationMillis))
        .signWith(secretKey)
        .compact();
  }

  @Override
  public boolean isValid(String token) {
    try {
      parseToken(token);
      return true;
    } catch (JwtException | IllegalArgumentException e) {
      return false;
    }
  }

  @Override
  public String getSubject(String token) {
    return parseToken(token).getBody().getSubject();
  }

  private Jws<Claims> parseToken(String token) {
    return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
  }
}
