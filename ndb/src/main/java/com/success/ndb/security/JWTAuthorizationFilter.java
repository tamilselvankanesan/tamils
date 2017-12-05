package com.success.ndb.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	private AuthenticationManager authenticationManager;
	public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
		this.authenticationManager = authenticationManager;
	}
	protected void onSuccessfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, Authentication authResult) throws IOException {
		System.out.println("Hello .... ");
	}
	
	protected void onUnsuccessfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException failed)
					throws IOException {
		System.out.println("Hello .... ");
	}
}
