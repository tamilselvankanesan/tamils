package com.success.ndb.assemblers;

import com.success.ndb.dto.PersonDTO;
import com.success.ndb.entities.Person;

public class PersonAssembler {

	public static Person assemble(PersonDTO dto){
		if(dto == null){
			return null;
		}
		Person person = new Person();
		person.setAbout(dto.getAbout());
		person.setAddress1(dto.getAddress1());
		person.setAddress2(dto.getAddress2());
//		person.setCity(dto.getCity());
//		person.setCountryCode(countryCode);
		person.setDistrict(dto.getDistrict());
		person.setFirstName(dto.getFirstName());
		person.setLastName(dto.getLastName());
		person.setPersonId(dto.getPersonId());
//		person.setReviews(reviews);
//		person.setState(state);
//		person.setTimelines(timelines);
		person.setVillage(dto.getVillage());
		person.setZipCode(dto.getZipCode());
		
		return person;
	}
}
