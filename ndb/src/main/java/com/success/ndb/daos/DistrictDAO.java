package com.success.ndb.daos;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.success.ndb.entities.District;
@Repository
@Transactional
public interface DistrictDAO extends CrudRepository<District, Integer>{
	@Query("select a from District a where LOWER(a.name)=:name and a.state.id=:id")
	List<District> findByNameAndStateId(@Param("name") String districtName, @Param("id") int stateId);
	
	@Query("select a from District a where a.state.id=:id")
	List<District> findByStateId(@Param("id") int stateId);
}
