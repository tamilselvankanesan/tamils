package com.success.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.success.clients.AddressClient;

@RestController
public class PersonController {

	@Autowired
	private AddressClient aClient;
	
	@GetMapping(path="/")
	public String getPerson(){
		System.out.println("Inside person controller........."+new Date());
		return aClient.getAddress("some person ID");
	}
}
