package br.com.smvtech.uaisperchat.application.service;

import br.com.smvtech.uaisperchat.domain.RefreshToken;
import br.com.smvtech.uaisperchat.domain.User;
import br.com.smvtech.uaisperchat.infrastructure.persistence.RefreshTokenRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RefreshTokenService {

  private final RefreshTokenRepository refreshTokenRepository;

  public RefreshTokenService(RefreshTokenRepository refreshTokenRepository) {
    this.refreshTokenRepository = refreshTokenRepository;
  }

  public RefreshToken createToken(User user) {
    final long REFRESH_TOKEN_EXPIRY_DAYS = 7;
    final long REFRESH_TOKEN_EXPIRY_HOURS = 24;
    final long REFRESH_TOKEN_EXPIRY_MINUTES = 60;
    final long REFRESH_TOKEN_EXPIRY_SECONDS = 60;

    RefreshToken token = new RefreshToken();
    token.setUser(user);
    token.setToken(UUID.randomUUID().toString());
    token.setExpiryDate(java.time.Instant.now().plusSeconds(REFRESH_TOKEN_EXPIRY_DAYS * REFRESH_TOKEN_EXPIRY_HOURS * REFRESH_TOKEN_EXPIRY_MINUTES * REFRESH_TOKEN_EXPIRY_SECONDS));
    return refreshTokenRepository.save(token);
  }

  public RefreshToken validate(String token) {
    RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
        .orElseThrow(() -> new RuntimeException("Refresh token inválido"));

    if (refreshToken.isExpired()) {
      refreshTokenRepository.delete(refreshToken);
      throw new RuntimeException("Refresh token expirado");
    }
    return refreshToken;
  }
}
