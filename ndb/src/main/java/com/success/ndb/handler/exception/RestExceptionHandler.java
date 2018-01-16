package com.success.ndb.handler.exception;

import javax.persistence.EntityExistsException;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.success.ndb.dto.ApplicationError;
import com.success.ndb.utils.JwtAuthenticationResponse;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<Object> buildResponseEntity(RuntimeException ex, WebRequest request){
		String errorMessage = "Bad request";
		NDBException exception = new NDBException(HttpStatus.INTERNAL_SERVER_ERROR, errorMessage, ex);
		return new ResponseEntity<>(exception, exception.getStatus());
	}
	@ExceptionHandler(value=EntityExistsException.class)
	public ResponseEntity<Object> buildResponseEntityForEntityExists(RuntimeException ex, WebRequest request){
		ApplicationError error = new ApplicationError(ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.OK);
	}
	@ExceptionHandler(value=BadCredentialsException.class)
	public ResponseEntity<Object> buildResponseEntityForBadCredentials(RuntimeException ex, WebRequest request){
		ApplicationError error = new ApplicationError(ex.getMessage());
		JwtAuthenticationResponse authResponse = new JwtAuthenticationResponse();
		authResponse.setError(true);
		authResponse.setMessage(error.getMessage());
		return new ResponseEntity<>(authResponse, HttpStatus.OK);
	}
}
