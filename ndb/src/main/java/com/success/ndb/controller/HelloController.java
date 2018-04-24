package com.success.ndb.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
@CrossOrigin
public class HelloController {

	@RequestMapping(path="/method1", method=RequestMethod.GET)
	public String sendSomething(){
		String s = "{\"name\":\"Martin\",\"count\":2,\"description\":\"some movie\"}";
		return s;
	}
}
