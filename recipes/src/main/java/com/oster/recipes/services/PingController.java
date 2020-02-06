package com.oster.recipes.services;

import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

	@GetMapping("/ping")
	public String ping() {
		return "Hello.. current time is "+new Date();
	}
}
