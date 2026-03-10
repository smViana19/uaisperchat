package br.com.smvtech.uaisperchat.presentation.mapper;

import br.com.smvtech.uaisperchat.domain.User;
import br.com.smvtech.uaisperchat.presentation.dto.UserRequestDTO;
import br.com.smvtech.uaisperchat.presentation.dto.UserResponseDTO;

public class UserMapper {
  public static User toEntity(UserRequestDTO dto) {
    if (dto == null) return null;
    User user = new User();
    user.setName(dto.name());
    user.setEmail(dto.email());
    user.setDocument(dto.document());
    user.setPassword(dto.password());
    return user;

  }

  public static UserResponseDTO toResponse(User user) {
    if(user == null) return null;
    return new UserResponseDTO(
      user.getId(),
      user.getName(),
      user.getEmail(),
      user.getDocument()
    );
  }
}
