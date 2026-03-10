package br.com.smvtech.uaisperchat.presentation.controller;

import br.com.smvtech.uaisperchat.application.service.UserService;
import br.com.smvtech.uaisperchat.presentation.dto.UserRequestDTO;
import br.com.smvtech.uaisperchat.presentation.dto.UserResponseDTO;
import br.com.smvtech.uaisperchat.presentation.mapper.UserMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@Tag(name = "Usuários", description = "Gerenciamento de usuários do sistema")
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }
  @PostMapping
  public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO dto, UriComponentsBuilder uriBuilder) {
    var user = UserMapper.toEntity(dto);
    var saved = userService.createUser(user);
    var response = UserMapper.toResponse(saved);

    var location = uriBuilder.path("/api/v1/users/{id}").buildAndExpand(saved.getId()).toUri();
    return ResponseEntity.created(location).body(response);
  }

//  public ResponseEntity<UserResponseDTO> getUserById(Long id) {
//    var user = userService.getUserById(id);
//    return ResponseEntity.ok(new UserResponseDTO(user.getId(), user.getName(), user.getEmail()));
//  }
}
