package com.success.ndb.assemblers;

import java.util.ArrayList;
import java.util.List;

import com.success.ndb.dto.CountryDTO;
import com.success.ndb.entities.Country;

public class CountryAssembler {

	public static CountryDTO assemble(Country country, boolean assembleState){
		if(country == null){
			throw new RuntimeException("Country Not Found.");
		}
		CountryDTO dto = new CountryDTO();
		dto.setCode(country.getCode());
		dto.setName(country.getName());
		if(assembleState && country.getStates()!=null){
			dto.setStates(StateAssembler.assemble(country.getStates(), false));
		}
		return dto;
	}
	public static List<CountryDTO> assemble(List<Country> countries, boolean assembleState){
		
		List<CountryDTO> list = new ArrayList<>();
		for(Country country : countries){
			list.add(assemble(country, assembleState));
		}
		return list;
	}
	public static List<CountryDTO> assemble(List<Country> countries){
		return assemble(countries, false);
	}
	public static CountryDTO assemble(Country country){
		return assemble(country, false);
	}
	
	public static Country assemble(CountryDTO countryDTO){
		if(countryDTO!=null){
			Country country = new Country();
			country.setCode(countryDTO.getCode());
			country.setName(country.getName());
			return country;
		}
		return null;
	}
}
