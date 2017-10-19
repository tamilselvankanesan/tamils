package com.success.ndb.service;

import java.util.List;

import com.success.ndb.dto.DistrictDTO;
import com.success.ndb.entities.District;

public interface DistrictService {

	DistrictDTO save(District entity);

	List<DistrictDTO> getDistricts(int stateId);
}
