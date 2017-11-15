package com.success.ndb.assemblers;

import com.success.ndb.dto.ApplicationUserDTO;
import com.success.ndb.entities.ApplicationUser;

public class ApplicationUserAssembler {

	public static ApplicationUserDTO assemble(ApplicationUser entity) {
		ApplicationUserDTO dto = new ApplicationUserDTO();
		dto.setApplicationLogin(entity.getApplicationLogin());
		dto.setApplicationPassword(entity.getApplicationPassword());
		dto.setUserId(entity.getUserId());
		dto.setFirstName(entity.getFirstName());
		dto.setLastName(entity.getLastName());
		return dto;
	}

	public static ApplicationUser assemble(ApplicationUserDTO dto) {
		ApplicationUser entity = new ApplicationUser();
		entity.setApplicationLogin(dto.getApplicationLogin());
		entity.setApplicationPassword(dto.getApplicationPassword());
		entity.setUserId(dto.getUserId());
		entity.setFirstName(dto.getFirstName());
		entity.setLastName(dto.getLastName());
		return entity;
	}
}
