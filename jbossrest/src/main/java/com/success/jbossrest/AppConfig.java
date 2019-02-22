package com.success.jbossrest;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiTemplate;

@Configuration
public class AppConfig {
	@Bean
	DataSource dataSource() throws NamingException{
		return (DataSource)new JndiTemplate().lookup("java:/jdbc/myndb");
	}

}
