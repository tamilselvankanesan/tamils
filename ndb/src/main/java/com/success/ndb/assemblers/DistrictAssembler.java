package com.success.ndb.assemblers;

import com.success.ndb.dto.DistrictDTO;
import com.success.ndb.entities.District;

public class DistrictAssembler {

	public District assemble(DistrictDTO dto){
		if(dto == null){
			return null;
		}
		District entity = new District();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setState(StateAssembler.assemble(dto.getState()));
		return entity;
	}
	public DistrictDTO assemble(District entity){
		if(entity == null){
			return null;
		}
		DistrictDTO dto = new DistrictDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		return dto;
	}
}
