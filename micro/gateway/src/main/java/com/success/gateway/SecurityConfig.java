package com.success.gateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	/*
	 * @Autowired
	 * 
	 * @Qualifier("customUserDetailsService") private UserDetailsService
	 * customUserDetailsService; // spring will use this class to load the user
	 * details
	 * 
	 * @Autowired private JwtAuthenticationEntryPoint unauthorizedHandler;
	 */

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// exclude the signup URL from authentication..
		// authenticationTokenFilterBean checks if a valid auth token presents. if doesn't present unauthorizedHandler will be called.
		
		//http.cors().and().csrf().disable().exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
		//.authorizeRequests().antMatchers("/**/admin/**").authenticated().anyRequest().permitAll().and()
		//.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class)
		//.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
//		http.cors().and().csrf().disable().exceptionHandling().and().authorizeRequests().antMatchers("/**").permitAll();
		http.cors().and().csrf().disable().authorizeRequests().antMatchers("/**").permitAll(); 
		
		//http.cors().and().csrf().disable().exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
		//.authorizeRequests().antMatchers("/**/user/**").permitAll().anyRequest().authenticated().and()
		//.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class)
		//.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("OPTIONS");
		config.addAllowedMethod("HEAD");
		config.addAllowedMethod("GET");
		config.addAllowedMethod("PUT");
		config.addAllowedMethod("POST");
		config.addAllowedMethod("DELETE");
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}
	
	/*
	 * @Override
	 * 
	 * @Bean public AuthenticationManager authenticationManagerBean() throws
	 * Exception { return super.authenticationManagerBean(); }
	 */

	/*
	 * @Autowired public void configureGlobal(AuthenticationManagerBuilder auth)
	 * throws Exception {
	 * auth.userDetailsService(customUserDetailsService).passwordEncoder(
	 * getPasswordEncoder()); }
	 */

	/*
	 * @Bean public JWTAuthenticationFilter authenticationTokenFilterBean() { return
	 * new JWTAuthenticationFilter(); }
	 */

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) {
		 System.out.println(new BCryptPasswordEncoder().encode("tamil"));
	}
}
