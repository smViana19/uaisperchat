package br.com.smvtech.uaisperchat.application.service;

import br.com.smvtech.uaisperchat.domain.User;
import br.com.smvtech.uaisperchat.infrastructure.persistence.UserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder encoder;

  public UserService(UserRepository userRepository, PasswordEncoder encoder) {
    this.userRepository = userRepository;
    this.encoder = encoder;
  }

  public User createUser(User user) {
    if (userRepository.existsByEmail(user.getEmail())) {
      throw new RuntimeException("Esse email já está em uso.");
    }
    String hashPassword = encoder.encode(user.getPassword());
    user.setPassword(hashPassword);
    return userRepository.save(user);
  }



}
