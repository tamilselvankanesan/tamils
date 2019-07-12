package com.success;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

import com.netflix.appinfo.AmazonInfo;
import com.success.util.aws.Constants;

//@Configuration
public class CloudConfig {

	private final Environment env;
	// Used as string.contains to search correct container
	// Make sure that your Docker container in AWS Task definition has this as part
	// of its name
	@Value("${aws.containerName: adddress-service}")
	private String containerName;

	public CloudConfig(Environment env) {
		this.env = env;
	}

	// for ec2 - not applicable for fargate type

	@Bean

	@Profile("aws")
	public EurekaInstanceConfigBean eurekaInstanceConfig(InetUtils inetUtils) {
		EurekaInstanceConfigBean config = new EurekaInstanceConfigBean(inetUtils);

		AmazonInfo info = AmazonInfo.Builder.newBuilder().autoBuild("eureka");
		System.out.println("amazon info ->" + info.toString());
		config.setDataCenterInfo(info);
		info.getMetadata().put(AmazonInfo.MetaDataKey.publicHostname.getName(),
				info.get(AmazonInfo.MetaDataKey.publicIpv4));
		config.setHostname(info.get(AmazonInfo.MetaDataKey.publicHostname));
		config.setIpAddress(info.get(AmazonInfo.MetaDataKey.publicIpv4)); //
		config.setNonSecurePort(getPortNumber());
		config.setDataCenterInfo(info);
		return config;
	}

//	@Bean
	@Profile("aws1")
	public EurekaInstanceConfigBean eurekaInstanceConfig1(InetUtils inetUtils) {

		System.out.println("container name is " + containerName);

		EurekaInstanceConfigBean config = new EurekaInstanceConfigBean(inetUtils);
		AmazonInfo info = AmazonInfo.Builder.newBuilder().autoBuild("eureka");
//		AmazonInfo.MetaDataKey.localIpv4;
		System.out.println("amazon info ->" + info.toString());
		config.setDataCenterInfo(info);

		try {
			String json = readEcsMetadata();
			System.out.println("ECS meta-data is " + json);
//			EcsTaskMetadata metadata = Converter.fromJsonString(json);
//			String ipAddress = findContainerPrivateIP(metadata);
			config.setIpAddress(getPrivateIpV4Address(json));
			config.setNonSecurePort(8080);
			getPortNumber();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return config;
	}

	private int getPortNumber() {
		System.out.println("port number is " + env.getProperty("server.port", Integer.class));
		return env.getProperty("server.port", Integer.class);
	}

	/*
	 * private String findContainerPrivateIP(EcsTaskMetadata metadata) { if (null !=
	 * metadata) { for (Container container : metadata.getContainers()) { boolean
	 * found = container.getName().toLowerCase().contains(containerName); if (found)
	 * { Network network = container.getNetworks()[0]; return
	 * network.getIPv4Addresses()[0]; } } } return ""; }
	 */
	private String readEcsMetadata() throws Exception {
		String url = Constants.AWS_METADATA_URL;
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

	private String getPrivateIpV4Address(String data) {
		try {
			JSONParser jsonParser = new JSONParser();
			JSONObject obj = (JSONObject) jsonParser.parse(data);
			JSONArray containers = (JSONArray) obj.get("Containers");
			for (Object containerObj : containers) {
				JSONObject container = (JSONObject) containerObj;
				if (!container.get("Name").toString().equals("address-service")) {
					continue;
				}
				JSONArray networks = (JSONArray) container.get("Networks");
				JSONObject nw = (JSONObject) networks.get(0);
				JSONArray ipv4Addresses = (JSONArray) nw.get("IPv4Addresses");
				System.out.println("ip address is " + ipv4Addresses.get(0));
				return (String) ipv4Addresses.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		JSONParser jsonParser = new JSONParser();
		try (FileReader reader = new FileReader("")) {
			// Read JSON file
			JSONObject obj = (JSONObject) jsonParser.parse(reader);
			JSONArray containers = (JSONArray) obj.get("Containers");
			for (Object containerObj : containers) {
				JSONObject container = (JSONObject) containerObj;
				if (!container.get("Name").toString().equals("address-service")) {
					continue;
				}
				JSONArray networks = (JSONArray) container.get("Networks");
				JSONObject nw = (JSONObject) networks.get(0);
				JSONArray ipv4Addresses = (JSONArray) nw.get("IPv4Addresses");
				System.out.println("dasdasdsa " + ipv4Addresses.get(0));
			}
			System.out.println("dasdasdsa");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}
}
