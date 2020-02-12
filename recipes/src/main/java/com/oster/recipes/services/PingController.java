package com.oster.recipes.services;

import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recipies")
public class PingController {

	@GetMapping("/ping")
	public String ping() {
		return "Hello.. current time is "+new Date();
	}
	@GetMapping("/all")
	public String ping1() {
		return "Hello get all.. current time is "+new Date();
	}
}
