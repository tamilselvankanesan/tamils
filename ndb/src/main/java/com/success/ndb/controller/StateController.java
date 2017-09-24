package com.success.ndb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.success.ndb.entities.State;
import com.success.ndb.service.StateService;

@RestController
@RequestMapping(path="/rest/state")
public class StateController {

	@Autowired
	private StateService stateService;
	
	@RequestMapping(method=RequestMethod.GET, path="{countryCode}")
	public List<State> getStates(@PathVariable String countryCode){
		return stateService.getStates(countryCode);
	}
}
