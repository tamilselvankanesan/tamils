package com.success.ndb.assemblers;

import com.success.ndb.dto.TimelineDTO;
import com.success.ndb.entities.Timeline;

public class TimelineAssembler {

	public static Timeline assemble(TimelineDTO dto) {

		if (dto == null) {
			return null;
		}
		Timeline entity = new Timeline();
		entity.setId(dto.getId());
		entity.setDescription(dto.getDescription());
		entity.setCreatedDatetime(dto.getCreatedDatetime());
		entity.setPerson(PersonAssembler.assemble(dto.getPerson()));
		return entity;
	}

	public static TimelineDTO assemble(Timeline entity) {

		if (entity == null) {
			return null;
		}
		TimelineDTO dto = new TimelineDTO();
		dto.setId(entity.getId());
		dto.setDescription(entity.getDescription());
		dto.setCreatedDatetime(entity.getCreatedDatetime());
		return dto;
	}
}
