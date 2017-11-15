package com.success.ndb.dto;

public class ApplicationUserDTO extends BaseDTO{
	private int userId;
	private String firstName;
	private String lastName;
	private String applicationLogin;
	private String applicationPassword;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getApplicationLogin() {
		return applicationLogin;
	}

	public void setApplicationLogin(String applicationLogin) {
		this.applicationLogin = applicationLogin;
	}

	public String getApplicationPassword() {
		return applicationPassword;
	}

	public void setApplicationPassword(String applicationPassword) {
		this.applicationPassword = applicationPassword;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
}
