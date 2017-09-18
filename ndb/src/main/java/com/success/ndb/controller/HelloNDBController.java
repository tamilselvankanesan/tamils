package com.success.ndb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.success.ndb.entities.Country;
import com.success.ndb.service.HelloNDBService;

@RestController
@RequestMapping(path="/rest/hellondb")
public class HelloNDBController {

	@Autowired
	private HelloNDBService ndbService;
	
	@RequestMapping(method=RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE})
	public List<Country> sayHello(){
		return ndbService.fetchCountries();
	}
}
