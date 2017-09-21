package com.success.ndb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.success.ndb.daos.CountryDao;
import com.success.ndb.entities.Country;

@Service
public class CountryServiceImpl implements CountryService {

	@Autowired
	private CountryDao countryDao;
	
	@Override
	public List<Country> getAllCountries() {
		return countryDao.findAll();
	}

}
