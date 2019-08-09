package com.success.aws;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;

import com.amazonaws.SDKGlobalConfiguration;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClient;
import com.amazonaws.services.rekognition.model.AmazonRekognitionException;
import com.amazonaws.services.rekognition.model.DetectLabelsRequest;
import com.amazonaws.services.rekognition.model.DetectLabelsResult;
import com.amazonaws.services.rekognition.model.DetectModerationLabelsResult;
import com.amazonaws.services.rekognition.model.Image;
import com.amazonaws.services.rekognition.model.Label;
import com.amazonaws.services.rekognition.model.ModerationLabel;
import com.amazonaws.util.IOUtils;

public class DetectModerationLabels
{
	
   public static void main(String[] args) throws Exception
   {
	   System.setProperty(SDKGlobalConfiguration.DISABLE_CERT_CHECKING_SYSTEM_PROPERTY, "true");
	   
      String photo = "C:\\Users\\btamilselvan\\OneDrive - Administrative Office of the U.S. Courts\\AO\\Tamil\\Google_Drive\\Documents\\study_material\\BNS\\profile-picture-960x640.jpg";
//      String bucket = "appjobs";
      
      AWSCredentialsProvider provider = new ProfileCredentialsProvider("app_user");
      
      AmazonRekognition rekognitionClient = AmazonRekognitionClient.builder().withCredentials(provider).build();
      
      ByteBuffer imageBytes;
      try (InputStream inputStream = new FileInputStream(new File(photo))) {
          imageBytes = ByteBuffer.wrap(IOUtils.toByteArray(inputStream));
      }
      
		/*
		 * DetectModerationLabelsRequest request = new DetectModerationLabelsRequest()
		 * .withImage(new Image().withS3Object(new
		 * S3Object().withName(photo).withBucket(bucket))) .withMinConfidence(90F);
		 */
      
      DetectLabelsRequest request = new DetectLabelsRequest()
              .withImage(new Image()
                      .withBytes(imageBytes))
              .withMaxLabels(10)
              .withMinConfidence(77F);
      try
      {
    	  
    	  DetectLabelsResult result = rekognitionClient.detectLabels(request);
          List <Label> labels = result.getLabels();

          System.out.println("Detected labels for " + photo);
          for (Label label: labels) {
             System.out.println(label.getName() + ": " + label.getConfidence().toString());
          }
    	  
			/*
			 * DetectModerationLabelsResult result =
			 * rekognitionClient.detectModerationLabels(request); List<ModerationLabel>
			 * labels = result.getModerationLabels();
			 * System.out.println("Detected labels for " + photo); for (ModerationLabel
			 * label : labels) { System.out.println("Label: " + label.getName() +
			 * "\n Confidence: " + label.getConfidence().toString() + "%" + "\n Parent:" +
			 * label.getParentName()); }
			 */
       }
       catch (AmazonRekognitionException e)
       {
         e.printStackTrace();
       }
    }
}