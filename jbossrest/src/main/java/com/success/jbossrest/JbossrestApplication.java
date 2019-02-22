package com.success.jbossrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages={"com.success.jbossrest"})
public class JbossrestApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(JbossrestApplication.class, args);
	}
}
