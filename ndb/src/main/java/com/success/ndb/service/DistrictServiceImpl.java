package com.success.ndb.service;

import java.util.List;

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
		DistrictDTO dto;
		if(exists(entity)){
			dto = new DistrictDTO();
			dto.setMessage("District Already Exists. ");
			dto.setError(true);
		}else{
			District newEntity = dao.save(entity);
			dto = DistrictAssembler.assemble(newEntity);
			dto.setSuccess(true);
			dto.setMessage("Save Successful.");
		}
		return dto;
	}

	@Override
	public List<DistrictDTO> getDistricts(int stateId) {
		return DistrictAssembler.assemble(dao.findByStateId(stateId));
	}
	private boolean exists(District entity){
		List<District> result = dao.findByNameAndStateId(entity.getName(), entity.getState().getId());
		return result!=null && !result.isEmpty();
	}

}
