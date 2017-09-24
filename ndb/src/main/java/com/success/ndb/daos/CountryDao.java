package com.success.ndb.daos;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.success.ndb.entities.Country;
@Repository
@Transactional
public interface CountryDao extends CrudRepository<Country, String> {

	List<Country> findAll();
	
	List<Country> save(List<Country> countries);
	
	@Query("Select a from Country a where LOWER(a.name) like %:filter%")
	List<Country> findCountries(@Param("filter") String filter);
}
