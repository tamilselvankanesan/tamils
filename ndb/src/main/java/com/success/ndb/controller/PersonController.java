package com.success.ndb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.success.ndb.service.PersonService;

@RestController
@RequestMapping("/rest/person")
@CrossOrigin
public class PersonController {

	@Autowired
	private PersonService personService;
}
