package com.success.ndb.service;

import java.util.List;

import com.success.ndb.entities.State;

public interface StateService {

	List<State> getAllStates();
	
	List<State> getStates(String countryCode);
	
	State save(State state);
}
