package com.success.ndb.daos;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.success.ndb.entities.Country;
@Repository
@Transactional
public interface CountryDao extends CrudRepository<Country, String> {

	List<Country> findAll();
	
	List<Country> save(List<Country> countries);
}
