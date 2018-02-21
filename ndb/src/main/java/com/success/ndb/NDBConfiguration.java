package com.success.ndb;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.util.StringUtils;

@Configuration
@PropertySource(value={"classpath:jdbc.properties"})
public class NDBConfiguration {

	//configure datasource, entity manager and transaction manager when needed to override the default properties. otherwise based on the properties given in the 
	// application.properties, spring will automatically create datasource, entity manager and transaction manager resources...
	@Autowired
	private Environment environment;
	
	@Value("${JDBC_URL:#{null}}")
	private String jdbcUrl;
	
	@Value("${JDBC_USER:#{null}}")
	private String jdbcUser;
	
	@Value("${JDBC_PASSWORD:#{null}}")
	private String jdbcPassword;
	
	@Bean
	DataSource dataSource(){
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(environment.getProperty("datasource.driver-class-name"));
		if(StringUtils.isEmpty(jdbcUrl)){
			dataSource.setUrl(environment.getProperty("datasource.url"));
		}else{
			dataSource.setUrl(jdbcUrl);	
		}
		if(StringUtils.isEmpty(jdbcUser)){
			dataSource.setUsername(environment.getProperty("datasource.username"));
		}else{
			dataSource.setUsername(jdbcUser);
		}
		if(StringUtils.isEmpty(jdbcPassword)){
			dataSource.setPassword(environment.getProperty("datasource.password"));
		}else{
			dataSource.setPassword(jdbcPassword);
		}
		return dataSource;
	}

	@Bean(name="entityManagerFactory")
	LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource){
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setDataSource(dataSource);
		factory.setPackagesToScan("com.success.ndb.entities");
		factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		factory.setJpaProperties(jpaHibernateProperties());
		return factory;
	}
	
	@Bean
	JpaTransactionManager transactionManager(DataSource dataSource, LocalContainerEntityManagerFactoryBean factory){
		JpaTransactionManager txnManager = new JpaTransactionManager();
		txnManager.setEntityManagerFactory(factory.getObject());
		return txnManager;
	}
	private Properties jpaHibernateProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", environment.getProperty("jpa.properties.hibernate.dialect"));
		properties.setProperty("hibernate.show_sql", environment.getProperty("jpa.show-sql"));
		return properties;
	}
}
