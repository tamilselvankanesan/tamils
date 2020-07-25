package com.success.websocket.security.services;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

@Component
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
  @Override
  public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest)
      throws OAuth2AuthenticationException {
    OAuth2User user = super.loadUser(oAuth2UserRequest);
    System.out.println("sdsadasd");
    return user;
  }
}
