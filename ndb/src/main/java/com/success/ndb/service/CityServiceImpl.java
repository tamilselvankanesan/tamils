package com.success.ndb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.success.ndb.assemblers.CityAssembler;
import com.success.ndb.daos.CityDAO;
import com.success.ndb.dto.CityDTO;
import com.success.ndb.entities.City;

@Service
public class CityServiceImpl implements CityService {

	@Autowired
	private CityDAO dao;

	@Override
	public CityDTO save(City entity) {
		CityDTO dto = null;
		if(exists(entity)){
			dto = new CityDTO();
			dto.setError(true);
			dto.setMessage("City already exists.");
		}else{
			dto = CityAssembler.assemble(dao.save(entity));
			dto.setMessage("Save Successful.");
		}
		return dto;
	}

	private boolean exists(City entity) {
		List<City> cities = dao.searchByCityNameAndDistrictId(entity.getName().toLowerCase(), entity.getDistrict().getId());
		if (cities != null && cities.size() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<CityDTO> searchByDistrictName(String districtName) {
		return null;
	}

	@Override
	public List<CityDTO> searchByDistrictId(int districtId) {
		return CityAssembler.assemble(dao.searchByDistrictId(districtId));
	}

}
