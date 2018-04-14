package com.success.programs.dto;

import java.io.Serializable;

/**
 * The persistent class for the district database table.
 * 
 */
public class District implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private String name;

	private State state;
	
	// bi-directional many-to-one association to city
//	private List<City> citites;

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

	/*public List<City> getCitites() {
		return citites;
	}

	public void setCitites(List<City> citites) {
		this.citites = citites;
	}*/
}