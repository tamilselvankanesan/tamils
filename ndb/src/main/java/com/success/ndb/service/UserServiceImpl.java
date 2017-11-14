package com.success.ndb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.success.ndb.daos.UserDAO;
import com.success.ndb.entities.ApplicationUser;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO dao;
	
	@Override
	public ApplicationUser save(ApplicationUser entity) {
		return dao.save(entity);
	}

}
