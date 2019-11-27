package com.success.s3;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.amazonaws.HttpMethod;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.DeleteObjectsRequest;
import com.amazonaws.services.s3.model.DeleteObjectsRequest.KeyVersion;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3ObjectSummary;

public class SimpleS3 {

//  public static void main(String[] args) throws InterruptedException, ExecutionException {
//    
//    //make sure credentials and config files are there under users/{username}/.aws directory.. with access key and security key and profile information
////    S3AsyncClient s3Client = S3AsyncClient.builder().region(Region.US_EAST_1).credentialsProvider(ProfileCredentialsProvider.builder().profileName("selvan").build()).build();
//    S3AsyncClient s3Client = S3AsyncClient.builder().region(Region.US_EAST_1).build(); // works ..uses the default profile
//    System.out.println(s3Client.toString());
//    System.out.println(s3Client.listBuckets().get().buckets().size());
//    s3Client.listBuckets().get().buckets().forEach(e -> System.out.println(e.name()));
//    
//    StsClient sClient = StsClient.builder().region(Region.US_EAST_1).credentialsProvider(ProfileCredentialsProvider.builder().profileName("selvan").build()).build();
//    
//    StsAsyncClient stsClient = StsAsyncClient.builder().region(Region.US_EAST_1).credentialsProvider(ProfileCredentialsProvider.builder().profileName("selvan").build()).build();
//    AssumeRoleRequest assumeRoleRequest = AssumeRoleRequest.builder().roleArn("arn:aws:iam::326286503284:role/some").roleSessionName("some").build();
////    System.out.println(stsClient.assumeRole(assumeRoleRequest).get().credentials().accessKeyId());
//    System.out.println(sClient.assumeRole(assumeRoleRequest).credentials());
//    
//    
////    AWSSecurityTokenService tokenService = AWSSecurityTokenServiceClientBuilder.standard().withCredentials().withRegion(Region.US_EAST_1.toString()).build();
//    
//    //the IAM user principal should be in the trust relationship, also the role should have a assumeRole permission policy
//    /*AssumeRoleRequest request = new AssumeRoleRequest().withRoleArn("arn:aws:iam::326286503284:role/some").withRoleSessionName("tamil")
//        .withDurationSeconds(3600);
//    AssumeRoleResult result = tokenService.assumeRole(request);
//    System.out.println(result.getCredentials().getAccessKeyId());*/
//  }

	public static void main(String[] args) {
		System.out.println("testing");
		List<KeyVersion> keyList = new ArrayList<KeyVersion>();

		AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_1)
				.withCredentials(new ProfileCredentialsProvider("app_user")).build();
		ListObjectsV2Request req = new ListObjectsV2Request().withBucketName("applied-jobs")
				.withPrefix("dev/resumes/a6867334-2e61-46d6-8665-6a485511bb66");
		ListObjectsV2Result result = s3Client.listObjectsV2(req);
		for (S3ObjectSummary objectSummary : result.getObjectSummaries()) {
			System.out.printf(" - %s (size: %d)\n", objectSummary.getKey(), objectSummary.getSize());
			keyList.add(new KeyVersion(objectSummary.getKey()));
		}
		keyList.add(new KeyVersion("927b996f-89c0-4f13-9ba2-3e729a231a32"));

		Date expiration = new Date();
		long expTimeMillis = expiration.getTime();
		expTimeMillis += 60000;
		expiration.setTime(expTimeMillis);

		GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest("applied-jobs",
				"dev/resumes/a6867334-2e61-46d6-8665-6a485511bb66/a6867334-2e61-46d6-8665-6a485511bb66.pdf").withMethod(HttpMethod.GET)
						.withExpiration(expiration);
		URL url = s3Client.generatePresignedUrl(generatePresignedUrlRequest);
		System.out.println(url.toString());

//         s3Client.deleteObjects(new DeleteObjectsRequest("applied-jobs1").withKeys(keyList));

	}

}
