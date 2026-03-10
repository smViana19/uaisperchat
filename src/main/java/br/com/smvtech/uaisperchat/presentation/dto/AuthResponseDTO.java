package br.com.smvtech.uaisperchat.presentation.dto;

public record AuthResponseDTO(String accessToken, String refreshToken, Long id, String name, String email, String document) {
}
