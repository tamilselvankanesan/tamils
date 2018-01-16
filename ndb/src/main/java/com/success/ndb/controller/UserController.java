package com.success.ndb.controller;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.success.ndb.dto.ApplicationUserDTO;
import com.success.ndb.utils.JwtAuthenticationResponse;
import com.success.ndb.utils.JwtUtil;

@RestController
@RequestMapping(path = "/rest/user")
@CrossOrigin
public class UserController {

	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUtil jwtUtil;
	private static final Logger logger = Logger.getLogger(UserController.class);
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@RequestMapping(path = "/signup", method = RequestMethod.POST)
	public ApplicationUserDTO signup(@RequestBody ApplicationUserDTO dto) {
		 dto.setApplicationPassword(passwordEncoder.encode(dto.getApplicationPassword()));
		/* return service.save(dto); */
		return null;
	}

	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public JwtAuthenticationResponse login(@RequestBody ApplicationUserDTO dto) {

		/*
		 * the below lines will make spring to use CustomUserDetailsService to
		 * authenticate the user and returns the authentication object if
		 * authentication is successful, otherwise 500 (BadCrendtialsException)
		 * which will be handled by RestExceptionHandler class
		 */

		final Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(dto.getApplicationLogin(),
						dto.getApplicationPassword(), new ArrayList<>()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		//after successful authentication load the user details and generate token
		UserDetails user = userDetailsService.loadUserByUsername(dto.getApplicationLogin());
		JwtAuthenticationResponse response = new JwtAuthenticationResponse();
		String token = jwtUtil.generateToken(user);
		response.setToken(token);
		logger.info("Authentication successful. ");
		return response;
	}

	@RequestMapping(path = "/info/{userName}", method = RequestMethod.GET)
	public ApplicationUserDTO getUserInfo(@PathVariable String userName) {
		return new ApplicationUserDTO();
	}
}
