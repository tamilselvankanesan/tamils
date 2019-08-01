package com.success.ndb.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
public class MailController {

	@PostMapping
	public String receive(HttpServletRequest request) {
		StringBuilder mail = new StringBuilder();
		mail.append("Subject: ");
		mail.append(request.getHeader("SUBJECT"));
		
		mail.append(" Body: ");
		mail.append(request.getHeader("HTML"));
		
		return mail.toString();
	}
}
