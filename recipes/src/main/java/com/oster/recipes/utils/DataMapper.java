package com.oster.recipes.utils;

import org.springframework.stereotype.Component;

import com.oster.recipes.dtos.UserDto;
import com.oster.recipes.entities.dynamodb.Data;

@Component
public class DataMapper {

	public UserDto toDto(Data data) {
		UserDto dto = new UserDto();
		dto.setFirstName(data.getFirstName());
		dto.setLastName(data.getLastName());
		dto.setPk(data.getPk());
		dto.setUserName(data.getCategory());
		return dto;
	}
	
	public Data toData(UserDto dto) {
		Data entity = new Data();
		entity.setFirstName(dto.getFirstName());
		entity.setLastName(dto.getLastName());
		entity.setPassword(dto.getPassword());
		entity.setPk(dto.getPk());
		entity.setCategory(dto.getUserName());
		return entity;
	}
	
}
