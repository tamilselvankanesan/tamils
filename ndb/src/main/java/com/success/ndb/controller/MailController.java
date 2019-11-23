package com.success.ndb.controller;

import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/rest/mail")
public class MailController {

	@PostMapping
	public Result receive(@RequestParam Map<String,String> allParams, @RequestHeader Map<String,String> allHeaders) {
		Result r = new Result();
		r.allParams = allParams;
		r.allHeaders = allHeaders;
		
		  StringBuilder mail = new StringBuilder(); allParams.forEach((key, value) -> {
		  
		  mail.append("Key: "); mail.append(key); mail.append("\n");
		  mail.append(" value: "); mail.append(value); mail.append("\n"); });
		  
		  System.out.println("mail content is "+mail.toString());
		 
		return r;
	}
	
	@XmlRootElement
	static class Result{
		public Map<String, String> getAllParams() {
			return allParams;
		}
		public void setAllParams(Map<String, String> allParams) {
			this.allParams = allParams;
		}
		public Map<String, String> getAllHeaders() {
			return allHeaders;
		}
		public void setAllHeaders(Map<String, String> allHeaders) {
			this.allHeaders = allHeaders;
		}
		Map<String,String> allParams;
		Map<String,String> allHeaders;
	}
}
