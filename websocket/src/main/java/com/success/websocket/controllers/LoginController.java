package com.success.websocket.controllers;

import java.security.Principal;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.success.websocket.dtos.UserDto;

@Controller
public class LoginController {

  @GetMapping("/p/mylogin")
  public String loginPage(Model model) {
    model.addAttribute("user", new UserDto());
    return "mylogin";
  }

  @PostMapping(path = "/p/login")
  public String login(UserDto user, Model model) {
    user.setFirstName("Tamil");
    user.setLastName("B");
    model.addAttribute("user", user);
    user.setToken(UUID.randomUUID().toString());
    return "home";
  }

  @GetMapping(path = "/welcome")
  public String welcome(Principal principal, Model model) {
    UserDto user = new UserDto();
    user.setFirstName("Tammy");
    model.addAttribute("user", user);
    return "welcome";
  }
}
