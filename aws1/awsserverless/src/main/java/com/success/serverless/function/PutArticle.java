package com.success.serverless.function;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.success.serverless.model.ServerlessInput;
import com.success.serverless.model.ServerlessOutput;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

/**
 * Lambda function that triggered by the API Gateway event "POST /". It reads all the query parameters as the metadata for this
 * article and stores them to a DynamoDB table. It reads the payload as the content of the article and stores it to a S3 bucket.
 */
public class PutArticle implements RequestHandler<ServerlessInput, ServerlessOutput> {
    // DynamoDB table name for storing article metadata.
    private static final String ARTICLE_TABLE_NAME = System.getenv("ARTICLE_TABLE_NAME");
    // DynamoDB table attribute name for storing article id.
    private static final String ARTICLE_TABLE_ID_NAME = "id";
    // DynamoDB table attribute name for storing the bucket name where holds the article's content.
    private static final String ARTICLE_TABLE_BUCKET_NAME = "bucket";
    // DynamoDB table attribute name for storing the bucket object key name that contains the article's content.
    private static final String ARTICLE_TABLE_KEY_NAME = "key";
    // S3 bucket name for storing article content.
    private static final String ARTICLE_BUCKET_NAME = System.getenv("ARTICLE_BUCKET_NAME");
    @Override
    public ServerlessOutput handleRequest(ServerlessInput serverlessInput, Context context) {
            // Using builder to create the clients could allow us to dynamically load the region from the AWS_REGION environment
            // variable. Therefore we can deploy the Lambda functions to different regions without code change.
    	
    		DynamoDbClient dynamoDb = DynamoDbClient.builder().build();
    		S3Client s3Client = S3Client.builder().build();
            ServerlessOutput output = new ServerlessOutput();

            try {
                String keyName = UUID.randomUUID().toString();
                String content = serverlessInput.getBody();
                
                PutObjectRequest putObjectRequest = PutObjectRequest.builder().bucket(ARTICLE_BUCKET_NAME).key(keyName).build();
                s3Client.putObject(putObjectRequest, RequestBody.fromBytes(content.getBytes(StandardCharsets.UTF_8)));
                
                Map<String, AttributeValue> attributes = convert(serverlessInput.getQueryStringParameters());
                attributes.putIfAbsent(ARTICLE_TABLE_ID_NAME, AttributeValue.builder().s(UUID.randomUUID().toString()).build());
                attributes.putIfAbsent(ARTICLE_TABLE_BUCKET_NAME, AttributeValue.builder().s(ARTICLE_BUCKET_NAME).build());
                attributes.put(ARTICLE_TABLE_KEY_NAME, AttributeValue.builder().s(keyName).build());
                
                PutItemRequest putItemRequest = PutItemRequest.builder().tableName(ARTICLE_TABLE_NAME).item(attributes).build();
                dynamoDb.putItem(putItemRequest);
                output.setStatusCode(200);
                output.setBody("Successfully inserted article " + attributes.get(ARTICLE_TABLE_ID_NAME).s());
            } catch (Exception e) {
                output.setStatusCode(500);
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw));
                output.setBody(sw.toString());
            }
        return output;
    }

    private Map<String, AttributeValue> convert(Map<String, String> map) {
    	return Optional.ofNullable(map).orElseGet(HashMap::new).entrySet().stream().collect(
                Collectors.toMap(x -> x.getKey(), x -> AttributeValue.builder().s(x.getValue()).build()));
    }
}