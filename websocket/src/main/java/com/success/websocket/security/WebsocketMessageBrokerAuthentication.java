package com.success.websocket.security;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebSocketMessageBroker
// we want web socket security takes precedence over web security to allow the inbound connections
// after validating the tokens...
@Order(Ordered.HIGHEST_PRECEDENCE)
public class WebsocketMessageBrokerAuthentication implements WebSocketMessageBrokerConfigurer {

  @Autowired private TokenProvider tokenProvider;

  @Override
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
            List<String> authorization = accessor.getNativeHeader("X-Authorization");
            if (StompCommand.CONNECT.equals(accessor.getCommand())) {
              if (tokenProvider.validateToken(authorization.get(0))) {
                log.info("authtoken", authorization.get(0));
                // validate token
                Authentication authentication =
                    new UsernamePasswordAuthenticationToken(
                        tokenProvider.getSubjectFromToken(authorization.get(0)),
                        null,
                        Collections.emptyList());
                accessor.setUser(authentication);
              } else {
                log.error("invalid crdentials");
                accessor.setUser(null);
              }
            } else if (StompCommand.SUBSCRIBE.equals(accessor.getCommand())) {
              if (tokenProvider.validateToken(authorization.get(0))) {
                String email = tokenProvider.getSubjectFromToken(authorization.get(0));
                log.info("email is ", email);
              } else {
                accessor.setUser(null);
              }
            }
            return message;
          }
        });
  }
}
