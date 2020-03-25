package com.oster.recipes.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.oster.recipes.utils.Constants;

@EnableWebSecurity
public class SecurityConfigurationDev extends WebSecurityConfigurerAdapter {

	@Autowired
	private JWTAuthenticationEntryPoint unauthorizedHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// http.csrf().disable().authorizeRequests().antMatchers("/**").permitAll();
		// //this will allow all requests
		http.csrf().disable().exceptionHandling()
				.authenticationEntryPoint(unauthorizedHandler).and()
				.authorizeRequests()
				.antMatchers(Constants.ALLOWED_URLS_LIST.toArray(new String[0]))
				.permitAll().anyRequest().authenticated().and()
				.addFilterBefore(authenticationTokenFilterBean(),
						UsernamePasswordAuthenticationFilter.class)
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public JWTAuthenticationFilter authenticationTokenFilterBean() {
		return new JWTAuthenticationFilter();
	}
}