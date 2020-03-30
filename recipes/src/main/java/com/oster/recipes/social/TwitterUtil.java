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
		  .setOAuthConsumerKey("")
		  .setOAuthConsumerSecret("")
//		  .setOAuthAccessToken("")
//		  .setOAuthAccessTokenSecret("");
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