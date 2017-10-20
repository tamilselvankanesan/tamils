package com.success.ndb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.success.ndb.assemblers.StateAssembler;
import com.success.ndb.daos.StateDAO;
import com.success.ndb.dto.StateDTO;
import com.success.ndb.entities.State;

@Service
public class StateServiceImpl implements StateService {

	@Autowired
	private StateDAO stateDao;

	@Override
	public List<StateDTO> getAllStates() {
		return StateAssembler.assemble(stateDao.findAll());
	}

	@Override
	public List<StateDTO> getStates(String countryCode) {
		List<State> states = stateDao.findByCountryCode(countryCode);
		return StateAssembler.assemble(states);
	}

	@Override
	public StateDTO save(State state) {
		StateDTO dto;
		if(exists(state)){
			dto = new StateDTO();
			dto.setError(true);
			dto.setMessage("State already exists.");
		}else{
			dto = StateAssembler.assemble(stateDao.save(state));
			dto.setMessage("Save successful.");
		}
		return dto;
	}

	private boolean exists(State entity) {
		List<State> states = stateDao.findByCountryCodeAndState(entity.getCountry().getCode(), entity.getCode().toLowerCase(), entity.getName().toLowerCase());
		return states!=null && states.size()>0;
	}

	@Override
	public StateDTO findByCode(String code) {
		return StateAssembler.assemble(stateDao.findByCode(code));
	}

}
