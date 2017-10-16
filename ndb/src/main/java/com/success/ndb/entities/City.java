package com.success.ndb.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.success.ndb.dto.PersonDTO;

/**
 * The persistent class for the city database table.
 * 
 */
@Entity
public class City implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String name;

	@Column(name = "state_id")
	private String stateId;

	// bi-directional many-to-one association to Person
	@OneToMany(mappedBy = "city", fetch = FetchType.LAZY)
	private List<PersonDTO> persons;

	public City() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStateId() {
		return this.stateId;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
	}

	public List<PersonDTO> getPersons() {
		return this.persons;
	}

	public void setPersons(List<PersonDTO> persons) {
		this.persons = persons;
	}
}