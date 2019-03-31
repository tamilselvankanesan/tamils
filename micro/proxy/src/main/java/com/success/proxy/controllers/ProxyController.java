package com.success.proxy.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProxyController {

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String sayHello() {
		return "Hello!, I am a proxy to my ndb micro services";
	}
}
