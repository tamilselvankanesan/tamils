package com.success.ndb.daos;

import java.util.List;

import com.success.ndb.dto.PersonDTO;
import com.success.ndb.entities.Person;

public interface PersonDAOCustom {

	public List<Person> search(PersonDTO dto);
}
