package com.success.ndb.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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

	@RequestMapping(path = "/signup", method = RequestMethod.POST)
	public ApplicationUserDTO signup(@RequestBody ApplicationUserDTO dto) {
		// dto.setApplicationPassword(bCryptPasswordEncoder.encode(dto.getApplicationPassword()));
		/*return service.save(dto);*/
		return null;
	}

	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public JwtAuthenticationResponse login(@RequestBody ApplicationUserDTO dto) {

		final Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(dto.getApplicationLogin(),
						dto.getApplicationPassword(), new ArrayList<>()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		UserDetails user = userDetailsService.loadUserByUsername(dto.getApplicationLogin());
		String token = jwtUtil.generateToken(user);
		
		System.out.println("testing");

		return new JwtAuthenticationResponse(token);
	}

	@RequestMapping(path = "/info/{userName}", method = RequestMethod.GET)
	public ApplicationUserDTO getUserInfo(@PathVariable String userName) {
		return new ApplicationUserDTO();
	}
}
