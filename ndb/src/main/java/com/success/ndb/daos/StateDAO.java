package com.success.ndb.daos;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.success.ndb.entities.State;

public interface StateDAO extends CrudRepository<State, Integer> {

	@Query("select a from state a where a.country.code = :countryCode")
	List<State> findByCountryCode(@Param("countryCode") String countryCode);
	
	List<State> findAll();
	
	@Query("select a from state a where a.code = :stateCode")
	State findByCode(@Param("stateCode") String stateCode);
	
	@Query("select a from state a where a.country.code = :countryCode and (LOWER(a.code)=:sCode or LOWER(a.name)=:sName)")
	List<State> findByCountryCodeAndState(@Param("countryCode") String countryCode, @Param("sCode") String stateCode, @Param("sName") String stateName);
}
