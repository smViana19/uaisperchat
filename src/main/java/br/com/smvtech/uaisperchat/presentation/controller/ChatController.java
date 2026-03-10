package br.com.smvtech.uaisperchat.presentation.controller;

import br.com.smvtech.uaisperchat.presentation.dto.ChatInputDTO;
import br.com.smvtech.uaisperchat.presentation.dto.ChatOutputDTO;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class ChatController {

  @MessageMapping("/new-message")
  @SendTo("/topics/messages")
  public ChatOutputDTO sendMessage(ChatInputDTO input) {
    return new ChatOutputDTO(HtmlUtils.htmlEscape(input.user() + ": " + input.message()));
  }


}
