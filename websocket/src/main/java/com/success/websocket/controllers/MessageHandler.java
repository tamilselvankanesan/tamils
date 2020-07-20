package com.success.websocket.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.success.websocket.models.Message;
import com.success.websocket.models.Reply;

@Controller
public class MessageHandler {

  @Autowired private SimpMessagingTemplate mt;

  @MessageMapping(value = "/incoming")
  @SendTo(value = "/topic/mydestination")
  public Reply handleMessage(
      Message message,
      @Header(name = "userId", defaultValue = "unknown") String userId,
      Principal user) {
    return new Reply(message.getName() + ": " + message.getContent());
  }

  @MessageMapping(value = "/cincoming")
  public void handleChatMessage(Message message) {
    // /mychat destination will be transformed to, /user/{message.getTo()}/mychat -> this is exactly
    // the path, the client subscribes to
    mt.convertAndSendToUser(
        message.getTo(), "/mychat", new Reply(message.getName() + ": " + message.getContent()));
  }
}
