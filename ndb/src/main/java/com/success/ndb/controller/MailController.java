package com.success.ndb.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/rest/mail")
public class MailController {

	@PostMapping
	public String receive(@RequestHeader Map<String, String> headers) {
		
		StringBuilder mail = new StringBuilder();
		headers.forEach((key, value) -> {
			
			mail.append("Key: ");
			mail.append(key);
			mail.append(" value: ");
			mail.append(value);
			mail.append("\n");
		});
		
		System.out.println("mail content is "+mail.toString());
		
		return mail.toString();
	}
}
