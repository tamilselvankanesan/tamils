package com.success.gateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
@Configuration
public class SecurityConfig{

	@Bean
    SecurityWebFilterChain authorization(ServerHttpSecurity security) {
		return security.authorizeExchange().pathMatchers("/**").permitAll().and().authorizeExchange().anyExchange().authenticated().and().build();
    }

	/*
	 * @Bean public CorsFilter corsFilter() { UrlBasedCorsConfigurationSource source
	 * = new UrlBasedCorsConfigurationSource(); CorsConfiguration config = new
	 * CorsConfiguration(); config.addAllowedOrigin("*");
	 * config.addAllowedHeader("*"); config.addAllowedMethod("OPTIONS");
	 * config.addAllowedMethod("HEAD"); config.addAllowedMethod("GET");
	 * config.addAllowedMethod("PUT"); config.addAllowedMethod("POST");
	 * config.addAllowedMethod("DELETE"); source.registerCorsConfiguration("/**",
	 * config); return new CorsFilter(source); }
	 */

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) {
		 System.out.println(new BCryptPasswordEncoder().encode("tamil"));
	}
}
