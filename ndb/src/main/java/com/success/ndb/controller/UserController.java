package com.success.ndb.controller;

import org.springframework.beans.factory.annotation.Autowired;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.success.ndb.dto.ApplicationUserDTO;
import com.success.ndb.service.UserService;

@RestController
@RequestMapping(path="/rest/user")
@CrossOrigin
public class UserController {

	@Autowired
	private UserService service;
//	@Autowired
//	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@RequestMapping(path = "/signup", method = RequestMethod.POST)
	public ApplicationUserDTO signup(@RequestBody ApplicationUserDTO dto) {
//		dto.setApplicationPassword(bCryptPasswordEncoder.encode(dto.getApplicationPassword()));
		return service.save(dto);
	}
	
	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public ApplicationUserDTO login(@RequestBody ApplicationUserDTO dto) {
		
		System.out.println("testing");
		
		return dto;
	}
	@RequestMapping(path="/info/{userName}", method=RequestMethod.GET)
	public ApplicationUserDTO getUserInfo(@PathVariable String userName){
		return new ApplicationUserDTO();
	}
}
