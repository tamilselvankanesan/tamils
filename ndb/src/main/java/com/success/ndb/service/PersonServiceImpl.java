package com.success.ndb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.success.ndb.daos.PersonDao;
import com.success.ndb.entities.Person;
@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonDao personDao;
	
	@Override
	public List<Person> getPersons() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person addPerson() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person updatePerson() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePerson() {
		// TODO Auto-generated method stub

	}

}
