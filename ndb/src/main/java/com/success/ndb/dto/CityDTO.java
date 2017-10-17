package com.success.ndb.dto;

public class CityDTO {
	private int id;
	private String name;
	private StateDTO state;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public StateDTO getState() {
		return state;
	}
	public void setStateId(StateDTO state) {
		this.state = state;
	}
}
