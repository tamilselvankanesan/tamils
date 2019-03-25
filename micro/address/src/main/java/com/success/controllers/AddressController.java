package com.success.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RefreshScope
public class AddressController {

	@Value("${message: default hello}")
	private String message;
	
	@GetMapping(path="/{personId}")
	public String getAddress(@PathVariable("personId") String personId){
		System.out.println("message from properties file "+message);
		return "from Address controller. Given person ID is "+personId+". Current date is "+new Date()+" My instance ID is "+this.toString();
	}
	
	@RequestMapping
	public String getAddress1(){
		return "from Address controller. Given person ID is Current date is "+new Date()+" My instance ID is "+this.toString();
	}
}
