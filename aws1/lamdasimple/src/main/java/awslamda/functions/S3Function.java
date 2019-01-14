package awslamda.functions;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.S3Object;

public class S3Function implements RequestHandler<S3Event, String> {

    
	private S3Client s3 = S3Client.builder().region(Region.US_EAST_1).build(); 

    public S3Function() {}

    // Test purpose only.
    S3Function(S3Client s3) {
        this.s3 = s3;
    }

    public String handleRequest(S3Event event, Context context) {
        context.getLogger().log("Received event is: " + event);
        
        // Get the object from the event and show its content type
        String bucket = event.getRecords().get(0).getS3().getBucket().getName();
        String key = event.getRecords().get(0).getS3().getObject().getKey();
        try {
        	
        	
            GetObjectResponse response= s3.getObject(GetObjectRequest.builder().bucket(bucket).key(key).build()).response();
            context.getLogger().log("Meta data: " + response.metadata());
            return response.metadata().toString();
        } catch (Exception e) {
            e.printStackTrace();
            context.getLogger().log(String.format(
                "Error getting object %s from bucket %s. Make sure they exist and"
                + " your bucket is in the same region as this function.", key, bucket));
        }
        return null;
    }
}