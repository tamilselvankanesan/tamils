package com.success.ndb.controller;

import java.util.List;

import javax.print.attribute.standard.Media;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.success.ndb.entities.Country;
import com.success.ndb.service.CountryService;

@RestController
@RequestMapping(path="/rest/countries")
public class CountryController {

	@Autowired	
	private CountryService countryService;
	@RequestMapping(produces=MediaType.APPLICATION_JSON_VALUE, method=RequestMethod.GET)
	public List<Country> getCountries(){
		return countryService.getAllCountries();
	}
}
