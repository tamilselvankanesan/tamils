package com.success.ndb.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/rest/mail")
public class MailController {

	private static final Logger logger = Logger.getLogger(MailController.class);
	
	@PostMapping
	public String receive(HttpServletRequest request) {
		StringBuilder mail = new StringBuilder();
		mail.append("Subject: ");
		mail.append(request.getHeader("SUBJECT"));
		
		mail.append(" Body: ");
		mail.append(request.getHeader("HTML"));
		
		logger.info("mail content is "+mail.toString());
		
		return mail.toString();
	}
}
