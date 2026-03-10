package br.com.smvtech.uaisperchat.infrastructure.persistence;

import br.com.smvtech.uaisperchat.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
  Optional<RefreshToken> findByToken(String token);

}
