package com.success.ndb.dto;

public class CityDTO extends BaseDTO{
	private int id;
	private String name;
	private DistrictDTO district;
	
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
	public DistrictDTO getDistrict() {
		return district;
	}
	public void setDistrict(DistrictDTO district) {
		this.district = district;
	}
}
