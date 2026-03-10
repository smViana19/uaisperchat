package br.com.smvtech.uaisperchat.infrastructure.persistence;


import br.com.smvtech.uaisperchat.domain.User;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
  boolean existsByEmail(String email);

  Optional<User> findByEmail(String email);
}
