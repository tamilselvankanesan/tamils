package com.success.ndb.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotBlank;

@Entity(name="applicationuser")
@Table(name = "application_user")
public class ApplicationUser {

	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "application_login")
	@NotBlank
	private String applicationLogin;
	
	@Column(name = "application_password")
	@NotBlank
	private String applicationPassword;
	
	@Column(name="user_id")
	@Id
	private int userId;
	@Transient
	private String token;

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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
