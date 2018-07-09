package com.success.ndb.dto;

import java.util.Date;

public class TimelineDTO {

	private int id;
	private Date createdDatetime;
	private String description;
	private PersonDTO person;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreatedDatetime() {
		return this.createdDatetime;
	}

	public void setCreatedDatetime(Date createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public PersonDTO getPerson() {
		return this.person;
	}

	public void setPerson(PersonDTO person) {
		this.person = person;
	}
}
