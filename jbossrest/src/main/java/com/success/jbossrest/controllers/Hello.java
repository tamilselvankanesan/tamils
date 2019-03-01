package com.success.jbossrest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.success.jbossrest.services.HelloService;

@RequestMapping("/")
@RestController
public class Hello {

	@Autowired
	private HelloService service;
	
	@RequestMapping(method = RequestMethod.GET, path="/hello")
	public String sayHello(){
		return service.someMethod();
	}
}
