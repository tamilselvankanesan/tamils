package com.oster.recipes.social;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@Component
public class TwitterUtil {

//	@Value("${twitter.api.key}")
	private String apiKey;
	
//	@Value("${twitter.api.secret.key}")
	private String apiSecretKey;
	
//	@Value("${twitter.access_token}")
	private String accessToken;
	
//	@Value("${twitter.access_token.secret}")
	private String accessTokenSecret;
	
	private Twitter client;
	
//	@PostConstruct
	public void init() {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey("DXIKf06dbix8doHHaKPDvCjal")
		  .setOAuthConsumerSecret("pFyjwo9s6aQAcwU05q5qJS9LtF8rPBjPzHiGkaWmOBKa4TdM6I")
//		  .setOAuthAccessToken("TdAqoA5IN4I31L4DL6QTYzIvJ5U6M3")
//		  .setOAuthAccessTokenSecret("RDT2lr8RqoA20uBqP78QcA9gcI2");
		  .setOAuthAccessToken("-")
		  .setOAuthAccessTokenSecret("");
		TwitterFactory tf = new TwitterFactory(cb.build());
		client = tf.getInstance();
	}
	
	public void tweet(String message) throws TwitterException {
		
		Status status = client.updateStatus("Welcome to tamils.rocks");
		System.out.println(status.getText());
	}
	
	public static void main(String[] args) {
		try {
			new TwitterUtil().tweet("test");
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}
}
