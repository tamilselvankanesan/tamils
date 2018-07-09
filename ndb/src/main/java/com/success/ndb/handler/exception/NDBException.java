package com.success.ndb.handler.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public class NDBException {
	private String message;
	private String debugMessage;
	@JsonFormat(pattern="MM/dd/yyyy hh:mm:ss", shape=Shape.SCALAR)
	private LocalDateTime timeStamp;
	private HttpStatus status;
	
	public NDBException(){
		setTimeStamp(LocalDateTime.now());
	}
	
	public NDBException(HttpStatus status, String message){
		this();
		this.status = status;
		this.message = message;
	}
	public NDBException(HttpStatus status, String message, RuntimeException exception){
		this();
		this.status = status;
		this.message = message;
		this.debugMessage = exception.getLocalizedMessage();
	}
	
	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDebugMessage() {
		return debugMessage;
	}

	public void setDebugMessage(String debugMessage) {
		this.debugMessage = debugMessage;
	}

	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}
}
