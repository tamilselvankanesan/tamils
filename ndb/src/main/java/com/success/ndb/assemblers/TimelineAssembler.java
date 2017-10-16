package com.success.ndb.assemblers;

import com.success.ndb.dto.TimelineDTO;
import com.success.ndb.entities.Timeline;

public class TimelineAssembler {

	public static Timeline assemble(TimelineDTO dto){
		
		if(dto == null){
			return null;
		}
		Timeline entity = new Timeline();
		entity.setId(dto.getId());
		entity.setDescription(dto.getDescription());
		entity.setCreatedDatetime(dto.getCreatedDatetime());
//		entity.setPerson(person);
		
		return null;
	}
}
