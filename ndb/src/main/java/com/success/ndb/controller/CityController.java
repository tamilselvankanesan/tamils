package com.success.ndb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.success.ndb.assemblers.CityAssembler;
import com.success.ndb.dto.CityDTO;
import com.success.ndb.service.CityService;

@RequestMapping("/rest/city")
@RestController
@CrossOrigin
public class CityController {

	@Autowired
	private CityService service;
	
	@RequestMapping(path="/save")
	public CityDTO save(@RequestBody CityDTO dto){
		return service.save(CityAssembler.assemble(dto)); 
	}
}
