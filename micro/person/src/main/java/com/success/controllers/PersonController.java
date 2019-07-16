package com.success.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.success.clients.AddressClient;

@RestController
@RefreshScope
public class PersonController {

	@Autowired
	private AddressClient aClient;

	@GetMapping(path = "/ping")
	public String ping() {
		System.out.println("Inside person controller.........Instanc ID is " + this.toString());
		return "I am a person controller and my instance ID is " + this.toString();
	}
	
	@GetMapping(path = "/")
	public String getPersonDefault() {
		System.out.println("Inside person controller.........Instanc ID is " + this.toString());
		return "Hello I am a person and my instance ID is " + this.toString();
	}

	@GetMapping(path = "/address/{personId}")
	public String getPerson(@PathVariable("personId") String personId) {
		System.out.println("Inside person controller.........Instanc ID is " + this.toString());
		return aClient.getAddress(personId);
	}
}
