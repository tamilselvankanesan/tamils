package com.success.ndb.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/rest/mail")
public class MailController {

	@PostMapping
	public String receive(@RequestParam Map<String,String> allParams) {
		
		StringBuilder mail = new StringBuilder();
		allParams.forEach((key, value) -> {
			
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
