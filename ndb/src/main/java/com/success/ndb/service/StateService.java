package com.success.ndb.service;

import java.util.List;

import com.success.ndb.dto.StateDTO;
import com.success.ndb.entities.State;

public interface StateService {

	List<StateDTO> getAllStates();

	List<StateDTO> getStates(String countryCode);

	StateDTO save(State state);

	StateDTO findByCode(String code);
}
