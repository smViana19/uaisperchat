package br.com.smvtech.uaisperchat.presentation.controller;

import br.com.smvtech.uaisperchat.application.service.AuthService;
import br.com.smvtech.uaisperchat.presentation.dto.AuthRequestDTO;
import br.com.smvtech.uaisperchat.presentation.dto.AuthResponseDTO;
import br.com.smvtech.uaisperchat.presentation.dto.RefreshTokenRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Autenticação", description = "Login de usuários")
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
  private final AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @Operation(
      summary = "Realizar login",
      description = "Autentica o usuário e retorna os tokens de acesso",
      requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
          description = "Credenciais do usuário",
          required = true,
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = AuthRequestDTO.class),
              examples = @ExampleObject(
                  name = "Exemplo de login",
                  summary = "JSON pronto para fazer login",
                  value = """
                      {
                        "email": "usuario@email.com",
                        "password": "senha123"
                      }
                      """
              )
          )
      ),
      responses = {
          @ApiResponse(responseCode = "200", description = "Login realizado com sucesso"),
          @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
      }
  )
  @PostMapping("/login")
  public AuthResponseDTO login(@Valid @RequestBody AuthRequestDTO dto) {
    return authService.login(dto);
  }

  @Operation(
      summary = "Atualizar token de acesso",
      description = "Gera um novo token de acesso usando o refresh token",
      requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
          description = "Refresh token para renovar o acesso",
          required = true,
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = RefreshTokenRequestDTO.class),
              examples = @ExampleObject(
                  name = "Exemplo de refresh token",
                  summary = "JSON pronto para renovar o token",
                  value = """
                      {
                        "refreshToken": "seu-refresh-token-aqui"
                      }
                      """
              )
          )
      ),
      responses = {
          @ApiResponse(responseCode = "200", description = "Token renovado com sucesso"),
          @ApiResponse(responseCode = "401", description = "Refresh token inválido ou expirado")
      }
  )
  @PostMapping("/refresh-token")
  public AuthResponseDTO refreshToken(@RequestBody String token) {
    return authService.refreshToken(token);
  }

}
