package com.success;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
//import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
//import org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Profile;

//import com.netflix.appinfo.AmazonInfo;

@SpringBootApplication
@EnableEurekaClient
public class AddressApplicaiton extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(AddressApplicaiton.class, args);
	}

	/*
	 * for ec2 - not applicable for fargate type
	 * @Bean
	 * 
	 * @Profile("aws") public EurekaInstanceConfigBean
	 * eurekaInstanceConfig(InetUtils inetUtils) { EurekaInstanceConfigBean config =
	 * new EurekaInstanceConfigBean(inetUtils);
	 * 
	 * AmazonInfo info = AmazonInfo.Builder.newBuilder().autoBuild("eureka");
	 * config.setDataCenterInfo(info);
	 * info.getMetadata().put(AmazonInfo.MetaDataKey.publicHostname.getName(),
	 * info.get(AmazonInfo.MetaDataKey.publicIpv4));
	 * config.setHostname(info.get(AmazonInfo.MetaDataKey.publicHostname));
	 * config.setIpAddress(info.get(AmazonInfo.MetaDataKey.publicIpv4)); //
	 * config.setNonSecurePort(port); config.setDataCenterInfo(info); return config;
	 * }
	 */

}
