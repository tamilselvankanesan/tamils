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
		dao.search(dto);
		return null;
	}

	@Override
	public PersonDTO addPerson(Person entity) {
		return PersonAssembler.assemble(dao.save(entity), false);
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
}
