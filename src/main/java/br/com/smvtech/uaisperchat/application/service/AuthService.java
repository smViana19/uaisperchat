package br.com.smvtech.uaisperchat.application.service;

import br.com.smvtech.uaisperchat.domain.User;
import br.com.smvtech.uaisperchat.infrastructure.config.auth.TokenProvider;
import br.com.smvtech.uaisperchat.infrastructure.persistence.UserRepository;
import br.com.smvtech.uaisperchat.presentation.dto.AuthRequestDTO;
import br.com.smvtech.uaisperchat.presentation.dto.AuthResponseDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthService {

  private final UserRepository userRepository;
  private final RefreshTokenService refreshTokenService;
  private final TokenProvider tokenProvider;
  private final PasswordEncoder encoder;


  public AuthService(UserRepository userRepository, PasswordEncoder encoder, TokenProvider tokenProvider,  RefreshTokenService refreshTokenService) {
    this.userRepository = userRepository;
    this.encoder = encoder;
    this.tokenProvider = tokenProvider;
    this.refreshTokenService = refreshTokenService;
  }

  public AuthResponseDTO login(AuthRequestDTO dto) {
    User user = userRepository.findByEmail(dto.email())
        .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

    if (!encoder.matches(dto.password(), user.getPassword())) {
      throw new RuntimeException("Senha ou email inválidos");
    }

    Map<String, Object> claims = Map.of();

    String accessToken = tokenProvider.generateToken(user.getEmail(), claims);
    var refreshToken = refreshTokenService.createToken(user);
    return new AuthResponseDTO(accessToken, refreshToken.getToken(), user.getId(), user.getName(), user.getEmail(), user.getDocument());
  }

  public AuthResponseDTO refreshToken(String token) {
    var refreshToken = refreshTokenService.validate(token);
    User user = refreshToken.getUser();
    Map<String, Object> claims = Map.of();
    String accessToken = tokenProvider.generateToken(user.getEmail(), claims);
    return new AuthResponseDTO(accessToken, refreshToken.getToken(), user.getId(), user.getName(), user.getEmail(), user.getDocument());
  }


}
