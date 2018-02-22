package com.success.ndb.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the state database table.
 * 
 */
@Entity(name="state")
public class State implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String code;

	private String name;

	// bi-directional many-to-one association to District
	@OneToMany(mappedBy = "state", fetch = FetchType.LAZY)
	private List<District> districts;

	// bi-directional many-to-one association to Country
	@ManyToOne
	private Country country;

	public State() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<District> getDistricts() {
		return this.districts;
	}

	public void setDistricts(List<District> districts) {
		this.districts = districts;
	}

	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
}