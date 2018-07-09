package com.success.ndb.service;

import java.util.List;

import com.success.ndb.dto.CityDTO;
import com.success.ndb.entities.City;

public interface CityService {

	CityDTO save(City entity);
	List<CityDTO> searchByDistrictName(String districtName);
	List<CityDTO> searchByDistrictId(int districtId);
}
