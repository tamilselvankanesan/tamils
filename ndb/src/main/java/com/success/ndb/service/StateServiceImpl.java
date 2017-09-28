package com.success.ndb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.success.ndb.daos.StateDAO;
import com.success.ndb.entities.State;
@Service
public class StateServiceImpl implements StateService{

	@Autowired
	private StateDAO stateDao;
	
	@Override
	public List<State> getAllStates() {
		return stateDao.findAll();
	}

	@Override
	public List<State> getStates(String countryCode) {
		List<State>  states = stateDao.findByCountryCode(countryCode); 
		return states;
	}

	@Override
	public State save(State state) {
		return null;
	}

}
