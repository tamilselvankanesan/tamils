package com.success.controllers;

import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class AddressController {

	@GetMapping(path="/{personId}")
	public String getAddress(@PathVariable("personId") String personId){
		return "from Address controller. Given person ID is "+personId+". Current date is "+new Date()+" My instance ID is "+this.toString();
	}
}
