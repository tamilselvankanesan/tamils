package com.success.websocket;

import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebSocketMessageBroker
//we want web socket security takes precedence over web security to allow the inbound connections after validating the tokens...
@Order(Ordered.HIGHEST_PRECEDENCE) 
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
    registry.addEndpoint("/myendpoint").withSockJS();
  }

  public void configureClientInboundChannel(ChannelRegistration registration) {
    registration.interceptors(
        new ChannelInterceptor() {
          @Override
          public Message<?> preSend(Message<?> message, MessageChannel channel) {
            StompHeaderAccessor accessor =
                MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
            /*
             * Intercept connect command and verify the authToken
             */
            if (StompCommand.CONNECT.equals(accessor.getCommand())) {
              List<String> authorization = accessor.getNativeHeader("X-Authorization");
              log.info("authtoken", authorization);
              // validate token
              Authentication authentication =
                  new UsernamePasswordAuthenticationToken(
                      authorization.get(0), null, Collections.emptyList());
              accessor.setUser(authentication);
            }
            if (StompCommand.SUBSCRIBE.equals(accessor.getCommand())) {
              // validate token corresponding against the subscription request
              log.info("testing...");
            }
            return message;
          }
        });
  }
}
