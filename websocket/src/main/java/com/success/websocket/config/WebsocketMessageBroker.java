package com.success.websocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebsocketMessageBroker implements WebSocketMessageBrokerConfigurer {

  @Override
  public void configureMessageBroker(MessageBrokerRegistry registry) {
    // handles the outgoing messages intended for destination "/topic", /user, etc.
    // clients subscribe to this destination to receive message
    registry.enableSimpleBroker("/topic", "/user");
    // client use this prefix.. for e.g. /myprefix/incoming.. where "incoming" is mapped to
    // MessageHandler
    registry.setApplicationDestinationPrefixes("/myprefix", "/chat");
    // for user to user messages
    registry.setUserDestinationPrefix("/user");
  }

  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
    registry.addEndpoint("/myendpoint").setAllowedOrigins("*").withSockJS();
  }
}
