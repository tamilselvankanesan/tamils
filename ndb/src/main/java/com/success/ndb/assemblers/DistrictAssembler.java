package com.success.ndb.assemblers;

import com.success.ndb.dto.DistrictDTO;
import com.success.ndb.entities.District;

public class DistrictAssembler {

	public District assemble(DistrictDTO dto, boolean assembleRelatedEntities){
		if(dto == null){
			return null;
		}
		District entity = new District();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		if(assembleRelatedEntities){
			entity.setState(StateAssembler.assemble(dto.getState()));			
		}
		return entity;
	}
	
}
