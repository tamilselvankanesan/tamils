package com.success.ndb.daos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.success.ndb.entities.ApplicationUser;

@Repository
public interface UserDAO extends CrudRepository<ApplicationUser, Integer> {

	@Query("select a from applicationuser a where a.applicationLogin = :applicationLogin")
	ApplicationUser findByApplicationLogin(@Param("applicationLogin") String applicationLogin);
}
