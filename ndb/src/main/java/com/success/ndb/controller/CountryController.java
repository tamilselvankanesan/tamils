package com.success.ndb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.success.ndb.entities.Country;
import com.success.ndb.service.CountryService;

@RestController
@RequestMapping(path = "/rest/countries")
@CrossOrigin
public class CountryController {

	@Autowired
	private CountryService countryService;

	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public List<Country> getCountries() {
		return countryService.getAllCountries();
	}

	@RequestMapping(method = RequestMethod.POST, path = "/import")
	public void importCountriesToNDB() {
		countryService.importCountries();
		System.out.println("Testing");
	}

	@RequestMapping(method = RequestMethod.GET, path = "{filter}")
	public List<Country> getCountries(@PathVariable String filter) {
		return countryService.getAllCountries(filter.toLowerCase());
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/find/{code}")
	public Country getCountry(@PathVariable String code){
		return countryService.findOne(code);
	}
}
