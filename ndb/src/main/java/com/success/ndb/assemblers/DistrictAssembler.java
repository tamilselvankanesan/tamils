package com.success.ndb.assemblers;

import java.util.ArrayList;
import java.util.List;

import com.success.ndb.dto.DistrictDTO;
import com.success.ndb.entities.District;

public class DistrictAssembler {

	public static District assemble(DistrictDTO dto){
		if(dto == null){
			return null;
		}
		District entity = new District();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setState(StateAssembler.assemble(dto.getState()));
		return entity;
	}
	public static DistrictDTO assemble(District entity){
		if(entity == null){
			return null;
		}
		DistrictDTO dto = new DistrictDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		return dto;
	}
	public static List<DistrictDTO> assemble(List<District> entities){
		List<DistrictDTO> result = new ArrayList<>();
		for(District entity : entities){
			result.add(assemble(entity));
		}
		return result;
	}
}
