package com.success.ndb.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class NDBSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("customUserDetailsService")
	private UserDetailsService customUserDetailsService; // spring will use this
															// class to load the
															// user details
	@Autowired
	private DemoAuthenticationProvider demoAuthenticationProvider;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// exclude the signup URL from authentication..
		// JWTAuthenticationFilter used to authenticate the user using the user
		// name and password and create a JWT token for successful login
		// JWTAuthorizationFilter used to authorize each calls and make sure if
		// the JWT token present and valid. if not valid then authorization
		// fails
		// http.authorizeRequests().antMatchers(HttpMethod.POST,
		// SecurityConstants.SIGN_UP_URL,
		// SecurityConstants.LOGIN_URL).permitAll().anyRequest().authenticated().
		// and().addFilter(new
		// JWTAuthenticationFilter(authenticationManager())).addFilter(new
		// JWTAuthorizationFilter(authenticationManager())).
		// sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		// http.addFilterBefore(new TokenAuthenticationFilter(),
		// BasicAuthenticationFilter.class);
		// http.addFilter(new JWTAuthorizationFilter(authenticationManager()));

		http.authorizeRequests().antMatchers("/rest/user/login").permitAll().anyRequest().authenticated().and()
				.addFilterBefore(new DemoAuthenticationFilter(), BasicAuthenticationFilter.class);
		// http.authorizeRequests().antMatchers("").authenticated().and().add

		// authenticationManager() will use our custom
		// AuthenticationManagerBuilder provided in the configureGlobal() method

	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(demoAuthenticationProvider);
	}

	/*
	 * @Autowired public void configureGlobal(AuthenticationManagerBuilder auth)
	 * throws Exception { // auth.authenticationProvider(getAuthProvider()); //
	 * auth.userDetailsService(customUserDetailsService);
	 * auth.authenticationProvider(demoAuthenticationProvider);
	 * 
	 * }
	 */

	/*
	 * @Bean public DaoAuthenticationProvider getAuthProvider() { //
	 * http://www.baeldung.com/spring-security-authentication-provider
	 * DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	 * authProvider.setUserDetailsService(customUserDetailsService);
	 * authProvider.setPasswordEncoder(getPasswordEncoder()); return
	 * authProvider; }
	 */
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) {
		// System.out.println(new BCryptPasswordEncoder().encode("tamil"));
	}
}
