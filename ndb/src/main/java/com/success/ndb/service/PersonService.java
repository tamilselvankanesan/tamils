package com.success.ndb.service;

import java.util.List;

import com.success.ndb.entities.Person;

public interface PersonService {

	List<Person> getPersons();
	Person addPerson();
	Person updatePerson();
	void deletePerson();
}
