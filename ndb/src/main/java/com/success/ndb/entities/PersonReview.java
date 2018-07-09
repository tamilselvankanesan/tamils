package com.success.ndb.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "PersonReview")
@DiscriminatorValue("person")
public class PersonReview extends Review{
	private static final long serialVersionUID = 1L;
}
