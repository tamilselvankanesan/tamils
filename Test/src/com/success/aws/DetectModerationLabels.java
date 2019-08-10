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
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.AmazonRekognitionException;
import com.amazonaws.services.rekognition.model.BoundingBox;
import com.amazonaws.services.rekognition.model.Celebrity;
import com.amazonaws.services.rekognition.model.DetectModerationLabelsRequest;
import com.amazonaws.services.rekognition.model.DetectModerationLabelsResult;
import com.amazonaws.services.rekognition.model.Image;
import com.amazonaws.services.rekognition.model.ModerationLabel;
import com.amazonaws.services.rekognition.model.RecognizeCelebritiesRequest;
import com.amazonaws.services.rekognition.model.RecognizeCelebritiesResult;
import com.amazonaws.util.IOUtils;

public class DetectModerationLabels
{
	
   public static void main(String[] args) throws Exception
   {
	   System.setProperty(SDKGlobalConfiguration.DISABLE_CERT_CHECKING_SYSTEM_PROPERTY, "true");
	   
      String photo = "C:\\\\Users\\\\btamilselvan\\\\Desktop\\\\310b3695dc70ea5c0505fab5a5666cb0.jpg";
//      String bucket = "appjobs";
      
      AWSCredentialsProvider provider = new ProfileCredentialsProvider("admin");
      
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
      
		/*
		 * DetectLabelsRequest request = new DetectLabelsRequest() .withImage(new
		 * Image() .withBytes(imageBytes)) .withMaxLabels(10) .withMinConfidence(77F);
		 */
      DetectModerationLabelsRequest request = new DetectModerationLabelsRequest().withImage(new Image().withBytes(imageBytes)).
    		  withMinConfidence(10f);
      try
      {
    	  
    	DetectModerationLabelsResult result = rekognitionClient.detectModerationLabels(request);
		List<ModerationLabel> labels = result.getModerationLabels();

          System.out.println("Detected labels for " + photo);
          for (ModerationLabel label: labels) {
        	  
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
//      detectCele();
    }
   private static void detectCele() {
	   String photo = "C:\\Users\\btamilselvan\\Desktop\\310b3695dc70ea5c0505fab5a5666cb0.jpg";

	   AWSCredentialsProvider provider = new ProfileCredentialsProvider("admin");
	      
	      AmazonRekognition rekognitionClient = AmazonRekognitionClient.builder().withCredentials(provider).build();

	      ByteBuffer imageBytes=null;
	      try (InputStream inputStream = new FileInputStream(new File(photo))) {
	         imageBytes = ByteBuffer.wrap(IOUtils.toByteArray(inputStream));
	      }
	      catch(Exception e)
	      {
	          System.out.println("Failed to load file " + photo);
	          System.exit(1);
	      }


	      RecognizeCelebritiesRequest request = new RecognizeCelebritiesRequest()
	         .withImage(new Image()
	         .withBytes(imageBytes));

	      System.out.println("Looking for celebrities in image " + photo + "\n");

	      RecognizeCelebritiesResult result=rekognitionClient.recognizeCelebrities(request);

	      //Display recognized celebrity information
	      List<Celebrity> celebs=result.getCelebrityFaces();
	      System.out.println(celebs.size() + " celebrity(s) were recognized.\n");

	      for (Celebrity celebrity: celebs) {
	          System.out.println("Celebrity recognized: " + celebrity.getName());
	          System.out.println("Celebrity ID: " + celebrity.getId());
	          BoundingBox boundingBox=celebrity.getFace().getBoundingBox();
	          System.out.println("position: " +
	             boundingBox.getLeft().toString() + " " +
	             boundingBox.getTop().toString());
	          System.out.println("Further information (if available):");
	          for (String url: celebrity.getUrls()){
	             System.out.println(url);
	          }
	          System.out.println();
	       }
	       System.out.println(result.getUnrecognizedFaces().size() + " face(s) were unrecognized.");
	   }
}