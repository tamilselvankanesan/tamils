package com.success.ndb.daos;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.success.ndb.entities.State;

public interface StateDAO extends CrudRepository<State, Integer> {

	@Query("select a from State a where a.country.code = :countryCode")
	List<State> findByCountryCode(@Param("countryCode") String countryCode);
	
	List<State> findAll();
}
