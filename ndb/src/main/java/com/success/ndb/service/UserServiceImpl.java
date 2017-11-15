package com.success.ndb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.success.ndb.assemblers.ApplicationUserAssembler;
import com.success.ndb.daos.UserDAO;
import com.success.ndb.dto.ApplicationUserDTO;
import com.success.ndb.entities.ApplicationUser;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO dao;
	
	@Override
	public ApplicationUserDTO save(ApplicationUserDTO dto) {
		ApplicationUser entity = dao.save(ApplicationUserAssembler.assemble(dto));
		return ApplicationUserAssembler.assemble(entity);
	}

	@Override
	public ApplicationUserDTO getPerson(ApplicationUserDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
