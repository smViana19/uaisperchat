package br.com.smvtech.uaisperchat.infrastructure.config.auth;

import br.com.smvtech.uaisperchat.domain.User;
import br.com.smvtech.uaisperchat.infrastructure.persistence.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;


@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
  private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
  private final TokenProvider tokenProvider;
  private final UserRepository userRepository;

  public JwtAuthenticationFilter(TokenProvider tokenProvider, UserRepository userRepository) {
    this.tokenProvider = tokenProvider;
    this.userRepository = userRepository;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    String header = request.getHeader("Authorization");
    if (header == null) {
      filterChain.doFilter(request, response);
      return;
    }

    String originalHeader = header;
    header = header.trim();
    final String bearerPrefix = "Bearer ";
    if (header.length() < bearerPrefix.length()) {
      filterChain.doFilter(request, response);
      return;
    }

    // case-insensitive check for prefix
    if (!header.regionMatches(true, 0, bearerPrefix, 0, bearerPrefix.length())) {
      filterChain.doFilter(request, response);
      return;
    }

    // extract token and normalize: remove possible duplicated "Bearer " if user pasted full 'Bearer <token>' into Swagger which also prefixes
    String token = header.substring(bearerPrefix.length()).trim();
    if (token.regionMatches(true, 0, bearerPrefix, 0, bearerPrefix.length())) {
      token = token.substring(bearerPrefix.length()).trim();
    }

    if (!tokenProvider.isValid(token)) {
      if (log.isDebugEnabled()) {
        log.debug("Invalid JWT token supplied. Header='{}', extracted='{}'", originalHeader, token);
      }
      filterChain.doFilter(request, response);
      return;
    }

    String email = tokenProvider.getSubject(token);
    User user = userRepository.findByEmail(email).orElseThrow();

    var auth = new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
    SecurityContextHolder.getContext().setAuthentication(auth);
    filterChain.doFilter(request, response);
  }
}
