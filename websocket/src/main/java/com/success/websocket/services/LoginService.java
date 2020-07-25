package com.success.websocket.services;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.success.websocket.dtos.UserDto;
import com.success.websocket.jpa.entities.User;
import com.success.websocket.jpa.repositories.UserRepository;
import com.success.websocket.security.TokenProvider;

@Service
public class LoginService {

  @Autowired private TokenProvider tokenProvider;
  @Autowired private UserRepository userRepo;
  @Autowired private ModelMapper mapper;

  public void authenticate(String email, String password, HttpServletResponse response) {
    //		StringU
    List<User> users = userRepo.findByEmail(email.toLowerCase().trim());
    if (users.isEmpty()) {
      throw new RuntimeException("user not found");
    }
    User user = users.get(0);
    if (user.getPassword().equals(password)) {
      // generate token
      String token = tokenProvider.createToken(user.getEmail());
      Cookie c = new Cookie("AuthToken", token);
      c.setHttpOnly(true);
//      c.setDomain("localhost");
      c.setPath("/");
      response.addCookie(c);
    } else {
      throw new RuntimeException("wrong credentials");
    }
  }

  public UserDto getUserByEmail(String email) {
    List<User> users = userRepo.findByEmail(email.toLowerCase().trim());
    if (users.isEmpty()) {
      throw new RuntimeException("user not found");
    }
    User user = users.get(0);
    return mapper.map(user, UserDto.class);
  }
}
