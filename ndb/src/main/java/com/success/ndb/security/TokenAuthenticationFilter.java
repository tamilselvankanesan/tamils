package com.success.ndb.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

public class TokenAuthenticationFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("testing...");

		final HttpServletRequest httpRequest = (HttpServletRequest) request;

		// extract token from header
		final String accessToken = httpRequest.getHeader("ndb-header");
		if(StringUtils.hasText(accessToken)){
			
		}else{
			String username = request.getParameter("user_name");
			String password = request.getParameter("password");
			User user = new User("tamil", "tamil111", new ArrayList<>());
			final UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

		}
		chain.doFilter(request, response);
	}

}
