package com.success;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;

public class GmailTest {

	private void test(){
		Properties props = new Properties();
		try {
			props.load(new FileInputStream(new File( "F:\\Tamil\\GIT_Repository\\Remote\\just-another\\Test\\src\\mail.properties" )));
			Session session = Session.getDefaultInstance(props, null);
			Store store = session.getStore("imaps");
			store.connect("smtp.gmail.com", "tamil.ts@gmail.com","muruga_muruga_21ts21");
			Folder inbox = store.getFolder("inbox");
			inbox.open(Folder.READ_ONLY);
			int messageCount = inbox.getMessageCount(); 
			Message[] messages = inbox.getMessages();
			System.out.println("------------------------------");
			 
			for (int i = 0; i < 10; i++) {
			    System.out.println("Mail Subject:- " + messages[i].getSubject());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new GmailTest().test();		
	}
}
