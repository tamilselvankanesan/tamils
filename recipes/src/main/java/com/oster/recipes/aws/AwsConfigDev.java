package com.oster.recipes.aws;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;

@Configuration
@Profile({"dev"})
public class AwsConfigDev {

	@Value("${aws.dynamodb.accesskey}")
	private String dynamoDBAccessKey;

	@Value("${aws.dynamodb.secretkey}")
	private String dynamoDBSecretKey;

	@Value("${aws.es.host}")
	private String elasticsearchHost;

	@Bean
	public AWSCredentials amazonAWSCredentials() {
		return new BasicAWSCredentials(dynamoDBAccessKey, dynamoDBSecretKey);
	}

	@Bean
	@Primary
	public DynamoDBMapperConfig dynamoDBMapperConfig() {
		return DynamoDBMapperConfig.DEFAULT;
	}

	@Bean
	@Primary
	public DynamoDBMapper dynamoDBMapper(AmazonDynamoDB amazonDynamoDB, DynamoDBMapperConfig config) {
		return new DynamoDBMapper(amazonDynamoDB, config);
	}

	@Bean
	public AmazonDynamoDB amazonDynamoDB() {
		return AmazonDynamoDBClientBuilder.standard().withCredentials(amazonAWSCredentialsProvider())
				.withRegion(Regions.US_EAST_1).build();
	}

	@Bean
	public DynamoDB dynamoDB() {
		return new DynamoDB(amazonDynamoDB());
	}

	public AWSCredentialsProvider amazonAWSCredentialsProvider() {
		return new AWSStaticCredentialsProvider(amazonAWSCredentials());
	}

	@Bean(destroyMethod = "close")
	public RestHighLevelClient client() {
		RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost(elasticsearchHost)));
		return client;

	}
}
