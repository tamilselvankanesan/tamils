package com.success.ndb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.success.ndb.assemblers.PersonAssembler;
import com.success.ndb.daos.PersonDAO;
import com.success.ndb.dto.PersonDTO;
import com.success.ndb.entities.Person;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonDAO dao;

	@Override
	public List<PersonDTO> search(PersonDTO dto) {
		return PersonAssembler.assemble(dao.search(dto), false);
	}

	@Override
	public PersonDTO addPerson(Person entity) {
		// validate??
		PersonDTO dto = PersonAssembler.assemble(dao.save(entity), false);
		dto.setSuccess(true);
		dto.setMessage("Save successful.");
		return dto;
	}

	@Override
	public PersonDTO updatePerson(Person entity) {
		return null;
	}

	@Override
	public void deletePerson(Person entity) {

	}

	private boolean exists(Person entity) {

		return false;
	}

	@Override
	public List<PersonDTO> search(String param) {
		return PersonAssembler.assemble(dao.search(param), false);
	}
}
