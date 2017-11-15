package com.success.ndb.service;

import com.success.ndb.dto.ApplicationUserDTO;

public interface UserService {

	ApplicationUserDTO save(ApplicationUserDTO dto);
	ApplicationUserDTO getPerson(ApplicationUserDTO dto);
}
