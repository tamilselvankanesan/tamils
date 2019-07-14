package com.success.ndb.daos;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.success.ndb.entities.Country;

@Repository
@Transactional
public interface HelloNDBDao extends CrudRepository<Country, Integer>{
}
