package com.success.ndb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories
@EntityScan(basePackages={"com.success.ndb.entities"})
public class NDBApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(NDBApplication.class, args);
	}
}
