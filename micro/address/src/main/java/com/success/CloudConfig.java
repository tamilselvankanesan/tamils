package com.success;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

import com.netflix.appinfo.AmazonInfo;
import com.success.util.aws.Constants;
import com.success.util.aws.Container;
import com.success.util.aws.Converter;
import com.success.util.aws.EcsTaskMetadata;
import com.success.util.aws.Network;

@Configuration
public class CloudConfig {

	private final Environment env;
	// Used as string.contains to search correct container
    // Make sure that your Docker container in AWS Task definition has this as part of its name
    @Value("${aws.containerName}")
    private String containerName;
	
	public CloudConfig(Environment env){
        this.env = env;
    }

	@Bean
	@Profile("aws")
	public EurekaInstanceConfigBean eurekaInstanceConfig(InetUtils inetUtils) {
		
		System.out.println("container name is "+containerName);

		EurekaInstanceConfigBean config = new EurekaInstanceConfigBean(inetUtils);
		AmazonInfo info = AmazonInfo.Builder.newBuilder().autoBuild("eureka");
		System.out.println("amazon info ->"+info.toString());
		config.setDataCenterInfo(info);

		try {
			String json = readEcsMetadata();
			System.out.println("ECS meta-data is "+json);
			EcsTaskMetadata metadata = Converter.fromJsonString(json);
			String ipAddress = findContainerPrivateIP(metadata);
			config.setIpAddress(ipAddress);
			config.setNonSecurePort(getPortNumber());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return config;
	}

	private int getPortNumber() {
		return env.getProperty("server.port", Integer.class);
	}

	private String findContainerPrivateIP(EcsTaskMetadata metadata) {
		if (null != metadata) {
			for (Container container : metadata.getContainers()) {
				boolean found = container.getName().toLowerCase().contains(containerName);
				if (found) {
					Network network = container.getNetworks()[0];
					return network.getIPv4Addresses()[0];
				}
			}
		}
		return "";
	}
	private String readEcsMetadata() throws Exception {
		String url = Constants.AWS_METADATA_URL+"/"+containerName;
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		StringBuilder response = new StringBuilder();
		try {
			con.setRequestMethod("GET");
			try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
				String inputLine;
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
			}
		} finally {
			con.disconnect();
		}
		return response.toString();
	}
}
