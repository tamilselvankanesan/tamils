package com.success.ndb.assemblers;

import java.util.ArrayList;
import java.util.List;

import com.success.ndb.dto.CityDTO;
import com.success.ndb.entities.City;

public class CityAssembler {

	public static City assemble(CityDTO dto){
		if(dto == null){
			return null;
		}
		City entity = new City();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setDistrict(DistrictAssembler.assemble(dto.getDistrict()));
		return entity;
	}
	public static CityDTO assemble(City entity){
		if(entity == null){
			return null;
		}
		CityDTO dto = new CityDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		return dto;
	}
	public static List<CityDTO> assemble(List<City> entities){
		List<CityDTO> list = new ArrayList<>();
		for(City entity: entities){
			list.add(assemble(entity));
		}
		return list;
	}
}
