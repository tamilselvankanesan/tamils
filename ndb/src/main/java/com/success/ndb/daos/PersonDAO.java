package com.success.ndb.daos;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.success.ndb.entities.Person;

@Transactional
public interface PersonDAO extends CrudRepository<Person, Integer>, PersonDAOCustom {

}
