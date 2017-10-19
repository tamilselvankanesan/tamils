package com.success.ndb.service;

import java.util.List;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.success.ndb.assemblers.DistrictAssembler;
import com.success.ndb.daos.DistrictDAO;
import com.success.ndb.dto.DistrictDTO;
import com.success.ndb.entities.District;
@Service
public class DistrictServiceImpl implements DistrictService {

	@Autowired
	private DistrictDAO dao;
	
	@Override
	public DistrictDTO save(District entity) {
		if(exists(entity)){
			throw new EntityExistsException("District already exists.");
		}
		District newEntity = dao.save(entity);
		return DistrictAssembler.assemble(newEntity);
	}

	@Override
	public List<DistrictDTO> getDistricts(int stateId) {
		return null;
	}
	private boolean exists(District entity){
		List<District> result = dao.findByNameAndStateId(entity.getName(), entity.getState().getId());
		return result!=null && !result.isEmpty();
	}

}
