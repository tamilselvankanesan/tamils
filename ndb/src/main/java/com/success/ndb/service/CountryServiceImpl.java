package com.success.ndb.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.success.ndb.daos.CountryDao;
import com.success.ndb.entities.Country;
import com.success.ndb.external.dto.Countries;

@Service
public class CountryServiceImpl implements CountryService {

	@Autowired
	private CountryDao countryDao;

	@Override
	public List<Country> getAllCountries() {
		return countryDao.findAll();
	}

	private List<Country> fetchFromExternalSource() {
		List<Country> result = new ArrayList<>();
		RestTemplate client = new RestTemplate();
		Countries countries = client.getForObject("http://vocab.nic.in/rest.php/country/json", Countries.class);
		if (countries != null) {
			for (com.success.ndb.external.dto.Country source : countries.getCountries()) {
				Country target = new Country();
				target.setCode(source.getCountry_id());
				target.setName(source.getCountry_name());
				result.add(target);
			}
			return result;
		}
		return null;
	}

	@Override
	public void importCountries() {
		List<Country> importedCountries = fetchFromExternalSource();
		long existingCount = countryDao.count();
		if (importedCountries.size() > existingCount) {
			for (Country c : importedCountries) {
				if (!countryDao.existsById(c.getCode())) {
					countryDao.save(c);
				}
			}
		}
	}

	@Override
	public List<Country> getAllCountries(String filter) {
		return countryDao.findCountries(filter);
	}

	@Override
	public Country findOne(String code) {
		return countryDao.findById(code).orElse(null);
	}
}
