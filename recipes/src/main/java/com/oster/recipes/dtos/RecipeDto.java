package com.oster.recipes.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class RecipeDto {
	private String pk;
	private String recipeId;
	private String title;
	private String pictureUrl;
	private String videoUrl;
	private String ingredients;
	private String preparation;
	private List<String> tags;
	private List<String> countries;
	private List<String> collections;
	private Float rating;
	private String createdOn;
	private String postedOn;
	private String scheduledOn;
	private SettingsDto seoSettings;
	private SettingsDto openGraphSettings;
	private List<PublicationSettingsDto> publications;
	private String type;
	
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class PublicationSettingsDto{
		private String country;
		private List<String> socialMedia; 
		private String productRelation;
		private String collection;
	}
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class SettingsDto{
		private String title;
		private String description;
	}
}
