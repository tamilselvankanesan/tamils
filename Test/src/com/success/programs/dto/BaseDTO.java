package com.success.programs.dto;

import java.util.Date;

public abstract class BaseDTO {

	private boolean warning;
	private boolean error;
	private boolean success;
	private String message;
	private Date createdDate;
	public boolean isWarning() {
		return warning;
	}
	public void setWarning(boolean warning) {
		this.warning = warning;
	}
	public boolean isError() {
		return error;
	}
	public void setError(boolean error) {
		this.error = error;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
  public Date getCreatedDate() {
    return createdDate;
  }
  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }
	
	
}
