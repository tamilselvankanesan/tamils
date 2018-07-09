package com.success.ndb.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.success.ndb.utils.JwtUtil;

public class JWTAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;
	
	@Value("${jwt.header}")
    private String tokenHeader;
	
	@Autowired
	private UserDetailsService userService;
	
	private static final Logger logger = Logger.getLogger(JWTAuthenticationFilter.class);
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
//		Authentication authentication = new UsernamePasswordAuthenticationToken("tamil", "tamil", new ArrayList<>());
//		SecurityContextHolder.getContext().setAuthentication(authentication);
		logger.info("inside demo auth filter...");
		
		String requestHeader = request.getHeader(tokenHeader);
		
		if(requestHeader!=null && requestHeader.startsWith("Bearer ")){
			logger.info("token found...");
			String token = requestHeader.substring(7);
			//validate the token
			String userName = jwtUtil.getUsernameFromToken(token);
			if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null){
				UserDetails userDetails = userService.loadUserByUsername(userName);
				if(jwtUtil.validateToken(token, userDetails)){
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//	                logger.info("authenticated user " + username + ", setting security context");
	                SecurityContextHolder.getContext().setAuthentication(authentication);
				}else{
					//invalid token
				}
			}
		}else{
			logger.info("no token found...");
			//access to secured urls will be denied
		}
		
		filterChain.doFilter(request, response);
	}
}
