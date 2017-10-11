package com.success.ndb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.success.ndb.assemblers.StateAssembler;
import com.success.ndb.dto.StateDTO;
import com.success.ndb.service.StateService;

@RestController
@RequestMapping(path="/rest/state")
@CrossOrigin
public class StateController {

	@Autowired
	private StateService stateService;
	
	@RequestMapping(method=RequestMethod.GET, path="{stateCode}")
	public StateDTO getState(@PathVariable String stateCode){
		return StateAssembler.assemble(stateService.findByCode(stateCode));
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/country/{countryCode}")
	public List<StateDTO> getStates(@PathVariable String countryCode){
		return StateAssembler.assemble(stateService.getStates(countryCode));
	}
	@RequestMapping(method=RequestMethod.POST, path="/add")
	public StateDTO addState(@RequestBody StateDTO stateDTO){
		return StateAssembler.assemble(stateService.save(StateAssembler.assemble(stateDTO)));
	}
}
