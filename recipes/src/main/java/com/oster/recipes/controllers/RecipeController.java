package com.oster.recipes.controllers;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oster.recipes.dtos.CollectionDto;
import com.oster.recipes.dtos.UserDto;
import com.oster.recipes.services.RecipeService;
import com.oster.recipes.utils.Result;

@RestController
public class RecipeController {

	@Autowired
	private RecipeService service;

	@GetMapping("/ping")
	public String ping() {
		return "Hello...current time is " + new Date();
	}

	@PostMapping("/login")
	public Result<UserDto> login(@RequestParam("username") String userName,
			@RequestParam("password") String password,
			HttpServletResponse response) {
		return service.login(userName, password, response);
	}

	@PostMapping("/add-user")
	public Result<UserDto> adddUser(@RequestBody UserDto user) {
		return service.addUser(user);
	}

	@PostMapping("/create-collection")
	public Result<CollectionDto> createCollection(
			@RequestBody CollectionDto dto, HttpServletRequest request) {
		return service.createCollection(dto, request);
	}
	
	@PostMapping("/create-recipe")
	public String createRecipe() {
		return "";
	}
}
