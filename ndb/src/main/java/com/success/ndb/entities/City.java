package com.success.ndb.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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

	@ManyToOne
	private State state;

	// bi-directional many-to-one association to Person
	@OneToMany(mappedBy = "city", fetch = FetchType.LAZY)
	private List<Person> persons;

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

	public State getState() {
		return this.state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public List<Person> getPersons() {
		return this.persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}
}