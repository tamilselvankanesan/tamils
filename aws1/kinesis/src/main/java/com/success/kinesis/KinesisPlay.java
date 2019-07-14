package com.success.kinesis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.kinesis.KinesisAsyncClient;
import software.amazon.awssdk.services.kinesis.model.PutRecordsRequest;
import software.amazon.awssdk.services.kinesis.model.PutRecordsRequestEntry;
import software.amazon.awssdk.services.kinesis.model.PutRecordsResponse;

public class KinesisPlay {

	private static final String STREAM_NAME="mydatastream";
	private void uploadToSream() {
		KinesisAsyncClient client = KinesisAsyncClient.builder().region(Region.US_EAST_1)
				.credentialsProvider(ProfileCredentialsProvider.builder().profileName("admin").build()).build();
		List<PutRecordsRequestEntry> putRecordsRequestEntryList = new ArrayList<>();
		for(int i=0; i<100; i++){
			putRecordsRequestEntryList.add(PutRecordsRequestEntry.builder().data(SdkBytes.fromByteArray(someData().getBytes())).partitionKey(String.valueOf(i)).build());
		}
		PutRecordsRequest request = PutRecordsRequest.builder().streamName(STREAM_NAME).records(putRecordsRequestEntryList).build();
		try {
			PutRecordsResponse putRecordsResponse = client.putRecords(request).get();
			System.out.println(putRecordsResponse);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}

	private String someData(){
		Map<String, String> data = new HashMap<>();
		for(int i=100; i<200; i++){
			data.put("some_key_"+i, "some value "+i);
			data.put("some_key_"+(i+1), "some value "+(i+1));
			data.put("some_date_"+(i+2), String.valueOf(System.currentTimeMillis()));
		}
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(data);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		new KinesisPlay().uploadToSream();
	}

}
