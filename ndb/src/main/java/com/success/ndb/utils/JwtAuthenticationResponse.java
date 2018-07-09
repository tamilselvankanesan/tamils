package com.success.ndb.utils;

import java.io.Serializable;

import com.success.ndb.dto.BaseDTO;

public class JwtAuthenticationResponse extends BaseDTO implements Serializable {

	private static final long serialVersionUID = 1250166508152483573L;

	private String token = null;

	public JwtAuthenticationResponse() {
	}
	
	public JwtAuthenticationResponse(String token) {
		this.token = token;
	}

	public String getToken() {
		return this.token;
	}
	public void setToken(String token) {
		this.token = token;
	}
}
