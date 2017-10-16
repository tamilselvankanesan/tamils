package com.success.ndb.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the district database table.
 * 
 */
@Entity
@NamedQuery(name = "District.findAll", query = "SELECT d FROM District d")
public class District implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	private State state;

	public District() {
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
}