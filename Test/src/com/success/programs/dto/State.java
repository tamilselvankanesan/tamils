package com.success.programs.dto;

import java.io.Serializable;
import java.util.List;

/**
 * The persistent class for the state database table.
 * 
 */
public class State extends BaseDTO implements Serializable  {
	private static final long serialVersionUID = 1L;

	private int id;

	private String code;

	private String name;
	
	private List<District> districts;

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