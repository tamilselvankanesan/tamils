package com.success.s3;

import java.util.concurrent.ExecutionException;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.sts.StsAsyncClient;
import software.amazon.awssdk.services.sts.StsClient;
import software.amazon.awssdk.services.sts.model.AssumeRoleRequest;

public class SimpleS3 {

  public static void main(String[] args) throws InterruptedException, ExecutionException {
    
    //make sure credentials and config files are there under users/{username}/.aws directory.. with access key and security key and profile information
//    S3AsyncClient s3Client = S3AsyncClient.builder().region(Region.US_EAST_1).credentialsProvider(ProfileCredentialsProvider.builder().profileName("selvan").build()).build();
    S3AsyncClient s3Client = S3AsyncClient.builder().region(Region.US_EAST_1).build(); // works ..uses the default profile
    System.out.println(s3Client.toString());
    System.out.println(s3Client.listBuckets().get().buckets().size());
    s3Client.listBuckets().get().buckets().forEach(e -> System.out.println(e.name()));
    
    StsClient sClient = StsClient.builder().region(Region.US_EAST_1).credentialsProvider(ProfileCredentialsProvider.builder().profileName("selvan").build()).build();
    
    StsAsyncClient stsClient = StsAsyncClient.builder().region(Region.US_EAST_1).credentialsProvider(ProfileCredentialsProvider.builder().profileName("selvan").build()).build();
    AssumeRoleRequest assumeRoleRequest = AssumeRoleRequest.builder().roleArn("arn:aws:iam::326286503284:role/some").roleSessionName("some").build();
//    System.out.println(stsClient.assumeRole(assumeRoleRequest).get().credentials().accessKeyId());
    System.out.println(sClient.assumeRole(assumeRoleRequest).credentials());
    
    
//    AWSSecurityTokenService tokenService = AWSSecurityTokenServiceClientBuilder.standard().withCredentials().withRegion(Region.US_EAST_1.toString()).build();
    
    //the IAM user principal should be in the trust relationship, also the role should have a assumeRole permission policy
    /*AssumeRoleRequest request = new AssumeRoleRequest().withRoleArn("arn:aws:iam::326286503284:role/some").withRoleSessionName("tamil")
        .withDurationSeconds(3600);
    AssumeRoleResult result = tokenService.assumeRole(request);
    System.out.println(result.getCredentials().getAccessKeyId());*/
  }

}
