package com.success.ndb.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	private AuthenticationManager authenticationManager;
	public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
		this.authenticationManager = authenticationManager;
	}

}
