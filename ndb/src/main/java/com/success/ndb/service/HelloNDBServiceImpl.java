package com.success.ndb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.success.ndb.daos.HelloNDBDao;
import com.success.ndb.daos.IHelloNDBRepository;
import com.success.ndb.entities.Country;

@Service
public class HelloNDBServiceImpl implements HelloNDBService {

	@Autowired
	private HelloNDBDao helloNdbDAO;
	
	@Autowired
	private IHelloNDBRepository helloRepo;
	
	@Override
	public List<Country> fetchCountries() {
		return helloRepo.findAll();
	}

}
