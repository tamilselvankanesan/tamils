package com.success.ndb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.success.ndb.assemblers.DistrictAssembler;
import com.success.ndb.dto.DistrictDTO;
import com.success.ndb.service.DistrictService;

@RestController
@RequestMapping("/rest/district")
@CrossOrigin
public class DistrictController {

	@Autowired
	private DistrictService service;
	
	@RequestMapping(path="/add", method=RequestMethod.POST)
	public DistrictDTO save(@RequestBody DistrictDTO dto){
		return service.save(DistrictAssembler.assemble(dto));
	}
	@RequestMapping(path="/search")
	public List<DistrictDTO> getDistricts(@RequestParam("stateId") int stateId){
		return service.getDistricts(stateId);
	}
}
