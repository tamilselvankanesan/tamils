package com.success.ndb.assemblers;

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
//		entity.setPersons(persons);
		return entity;
	}
}
