package com.success.programs.dto;

import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the country database table.
 * 
 */
public class Country extends BaseDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private String code;

	private String name;
	
	//bi-directional many-to-one association to State
	private List<State> states;

	public Country() {
	}

	public Country(String code, String name) {
	  this.code = code;
	  this.name = name;
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

	public List<State> getStates() {
		return this.states;
	}

	public void setStates(List<State> states) {
		this.states = states;
	}
  
}