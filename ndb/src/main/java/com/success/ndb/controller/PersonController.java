package com.success.ndb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.success.ndb.assemblers.PersonAssembler;
import com.success.ndb.dto.PersonDTO;
import com.success.ndb.service.PersonService;

@RestController
@RequestMapping("/rest/person")
@CrossOrigin
public class PersonController {

	@Autowired
	private PersonService personService;

	@RequestMapping("/save")
	public PersonDTO save(@RequestBody PersonDTO dto) {
		return personService.addPerson(PersonAssembler.assemble(dto));
	}
	@RequestMapping("/search")
	public List<PersonDTO> search(PersonDTO dto){
		return personService.search(dto);
	}
	@RequestMapping("/search/{param}")
	public List<PersonDTO> search(@PathVariable String param){
		return personService.search(param);
	}
}
