package com.success.ndb.handler.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<Object> buildResponseEntity(RuntimeException ex, WebRequest request){
		String errorMessage = "Bad request";
		NDBException exception = new NDBException(HttpStatus.BAD_REQUEST, errorMessage, ex);
		return new ResponseEntity<>(exception, exception.getStatus());
	}
}
