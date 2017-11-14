package com.success.ndb.daos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.success.ndb.entities.ApplicationUser;

@Repository
public interface UserDAO extends CrudRepository<ApplicationUser, Integer>{

}
