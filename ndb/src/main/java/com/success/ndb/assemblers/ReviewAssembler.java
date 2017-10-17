package com.success.ndb.assemblers;

import com.success.ndb.dto.PersonReviewDTO;
import com.success.ndb.dto.ReviewDTO;
import com.success.ndb.entities.PersonReview;
import com.success.ndb.entities.Review;

public class ReviewAssembler {

	public static Review assemble(ReviewDTO dto){
		if(dto == null){
			return null;
		}
		PersonReview entity = new PersonReview();
		entity.setCreatedDatetime(dto.getCreatedDatetime());
		entity.setDescription(dto.getDescription());
		entity.setId(dto.getId());
		entity.setRating(dto.getRating());
		entity.setPerson(PersonAssembler.assemble(dto.getPerson()));
		return entity;
	}
	public static ReviewDTO assemble(Review entity){
		if(entity == null){
			return null;
		}
		ReviewDTO dto = new PersonReviewDTO();
		dto.setCreatedDatetime(entity.getCreatedDatetime());
		dto.setDescription(entity.getDescription());
		dto.setId(entity.getId());
		dto.setRating(entity.getRating());
		return dto;
	}
}
