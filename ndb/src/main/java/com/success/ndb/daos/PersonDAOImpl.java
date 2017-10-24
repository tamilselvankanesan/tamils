package com.success.ndb.daos;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.success.ndb.dto.PersonDTO;
import com.success.ndb.entities.Person;

public class PersonDAOImpl implements PersonDAOCustom {

	/*
	 * If the name of your jpa repository interface is LocaleJpaRepository, your
	 * new custom interface should be named LocaleJpaRepositoryCustom, but the
	 * class that makes the override in the method must be named
	 * LocaleJpaRepositoryImpl, as it follows:
	 * 
	 * public class LocalJpaRepositoryImpl implements LocalJpaRepositoryCustom{
	 * 
	 * @Override public void customMethod(){....} } Basically, the
	 * implementation class of your custom interface should start with the name
	 * of your repository interface (JPARepository) ending with 'Impl' keyword.
	 */
	@Autowired
	private EntityManager em;

	@Override
	public List<Person> search(PersonDTO dto) {
		return null;
	}

}
