package com.success.serverless.function;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.util.IOUtils;
import com.success.serverless.model.ServerlessInput;
import com.success.serverless.model.ServerlessOutput;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;
import software.amazon.awssdk.services.dynamodb.model.GetItemResponse;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;

/**
 * Lambda function that triggered by the API Gateway event "GET /". It reads query parameter "id" for the article id and retrieves
 * the content of that article from the underlying S3 bucket and returns the content as the payload of the HTTP Response.
 */
public class GetArticle implements RequestHandler<ServerlessInput, ServerlessOutput> {
    // DynamoDB table name for storing article metadata.
    private static final String ARTICLE_TABLE_NAME = System.getenv("ARTICLE_TABLE_NAME");
    // DynamoDB table attribute name for storing article id.
    private static final String ARTICLE_TABLE_ID_NAME = "id";
    // DynamoDB table attribute name for storing the bucket object key name that contains the article's content.
    private static final String ARTICLE_TABLE_KEY_NAME = "key";
    // S3 bucket name for storing article content.
    private static final String ARTICLE_BUCKET_NAME = System.getenv("ARTICLE_BUCKET_NAME");
    @Override
    public ServerlessOutput handleRequest(ServerlessInput serverlessInput, Context context) {
        // Using builder to create the clients could allow us to dynamically load the region from the AWS_REGION environment
        // variable. Therefore we can deploy the Lambda functions to different regions without code change.
    	DynamoDbClient dynamoDb = DynamoDbClient.builder().build();
        ServerlessOutput output = new ServerlessOutput();
        
        try {
            if (serverlessInput.getQueryStringParameters() == null || serverlessInput.getQueryStringParameters().get(ARTICLE_TABLE_ID_NAME) == null) {
                    throw new Exception("Parameter " + ARTICLE_TABLE_ID_NAME + " in query must be provided!");
            }
            Map<String, AttributeValue> key = new HashMap<String, AttributeValue>();
            key.put(ARTICLE_TABLE_ID_NAME, AttributeValue.builder().s(serverlessInput.getQueryStringParameters().get(ARTICLE_TABLE_ID_NAME)).build());
            
            GetItemResponse response = dynamoDb.getItem(GetItemRequest.builder().tableName(ARTICLE_TABLE_NAME).key(key).build());
            String objectKey = response.getValueForField(ARTICLE_TABLE_KEY_NAME, String.class).get();
            
            S3Client s3Client = S3Client.builder().build();
            GetObjectRequest s3ObjectRequest = GetObjectRequest.builder().bucket(ARTICLE_BUCKET_NAME).key(objectKey).build();
            String content = IOUtils.toString(s3Client.getObject(s3ObjectRequest));
            
            output.setStatusCode(200);
            output.setBody(content);
            /*AttributeValue.builder().s(ARTICLE_TABLE_ID_NAME);
            
            key.put(ARTICLE_TABLE_ID_NAME, new AttributeValue().withS(serverlessInput.getQueryStringParameters().get(ARTICLE_TABLE_ID_NAME)));
            Map<String, AttributeValue> item = dynamoDb.getItem(new GetItemRequest()
                    .withTableName(ARTICLE_TABLE_NAME)
                    .withKey(key))
                    .getItem();
            String s3ObjectKey = item.get(ARTICLE_TABLE_KEY_NAME).getS();
            String content = IOUtils.toString(s3.getObject(new GetObjectRequest(ARTICLE_BUCKET_NAME, s3ObjectKey)).getObjectContent());
            output.setStatusCode(200);
            output.setBody(content);*/
        } catch (Exception e) {
            output.setStatusCode(500);
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            output.setBody(sw.toString());
        }

        return output;
    }
}