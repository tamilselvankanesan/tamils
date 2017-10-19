package com.success.ndb.dto;

public class ApplicationError {
	private String message;

	public ApplicationError(String message){
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
