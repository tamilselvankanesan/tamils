package com.success.ndb.assemblers;

import java.util.ArrayList;
import java.util.List;

import com.success.ndb.dto.PersonDTO;
import com.success.ndb.entities.Person;

public class PersonAssembler {

	public static List<Person> assemble(List<PersonDTO> dtos){
		if(dtos==null){
			return null;
		}
		List<Person> entities = new ArrayList<>();
		for(PersonDTO dto: dtos){
			entities.add(assemble(dto));
		}
		return entities;
	}
	
	public static Person assemble(PersonDTO dto){
		if(dto == null){
			return null;
		}
		Person person = new Person();
		person.setAbout(dto.getAbout());
		person.setAddress1(dto.getAddress1());
		person.setAddress2(dto.getAddress2());
		person.setCity(dto.getCity());
		person.setDistrict(dto.getDistrict());
		person.setFirstName(dto.getFirstName());
		person.setLastName(dto.getLastName());
		person.setPersonId(dto.getPersonId());
		person.setVillage(dto.getVillage());
		person.setZipCode(dto.getZipCode());
		person.setCountry(dto.getCountry());
		person.setState(dto.getState());
		return person;
	}
	public static PersonDTO assemble(Person entity, boolean assembleRelatedEntity){
		if(entity == null){
			return null;
		}
		PersonDTO dto = new PersonDTO();
		dto.setAbout(entity.getAbout());
		dto.setAddress1(entity.getAddress1());
		dto.setAddress2(entity.getAddress2());
		dto.setDistrict(entity.getDistrict());
		dto.setFirstName(entity.getFirstName());
		dto.setLastName(entity.getLastName());
		dto.setPersonId(entity.getPersonId());
		dto.setVillage(entity.getVillage());
		dto.setZipCode(entity.getZipCode());
		dto.setCity(entity.getCity());
		dto.setState(entity.getState());
		dto.setCountry(entity.getCountry());
		if(assembleRelatedEntity){
			entity.getReviews();
		}
		return dto;
	}
	public static List<PersonDTO> assemble(List<Person> entities, boolean assembleRelatedEntity){
		if(entities==null){
			return null;
		}
		List<PersonDTO> dtos = new ArrayList<>();
		for(Person entity: entities){
			dtos.add(assemble(entity, assembleRelatedEntity));
		}
		return dtos;
	}
}
