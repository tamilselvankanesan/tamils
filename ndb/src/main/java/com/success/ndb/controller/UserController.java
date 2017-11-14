package com.success.ndb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.success.ndb.entities.ApplicationUser;
import com.success.ndb.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService service;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@RequestMapping(path="/signup", method=RequestMethod.POST)
	public void signup(@RequestBody ApplicationUser user){
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		service.save(user);
	}
}
