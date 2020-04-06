package com.oster.recipes.entities.dynamodb;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.oster.recipes.utils.Constants;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@DynamoDBTable(tableName = "data")
public class Data {

	@Id
	@DynamoDBIgnore
	private DataKey key;
	private String pictureUrl;
	private String videoUrl;
	private String categoryName;
	private String ingredients;
	private String preparation;
	private List<String> tags;
	private List<String> collections;
	private String countryGsiPk;
	private List<String> countries;
	private Float rating;
	private List<PublicationSettings> publications;
	private String createdOn;
	private String postedOn;
	private String scheduledOn;
	private Settings seoSettings;
	private Settings openGraphSettings;
	@Getter
	@Setter
	private String firstName;
	@Getter
	@Setter
	private String lastName;

	@Getter
	@Setter
	private String password;
	
	@Getter @Setter
	private String type;

	/**
	 * id -> page id key -> page name value -> page token
	 */
	@Getter
	@Setter
	private List<DataMap> fbPageMapList;

	/**
	 * id -> page id key -> auth token value -> token secret
	 */
	@Getter
	@Setter
	private List<DataMap> twitterData;

	public Data() {
	}

	public Data(String pk, String category) {
		this.setPk(pk);
		this.setCategory(category);
	}

	public Data(String pk, String category, String categoryName) {
		this.setPk(pk);
		this.setCategory(category);
		this.categoryName = categoryName;
	}

	@DynamoDBDocument
	@NoArgsConstructor
	@AllArgsConstructor
	public class DataKey implements Serializable {
		private static final long serialVersionUID = 1L;
		/**
		 * can be one of the following: user Id, countries
		 * 
		 * pk
		 */
		private String pk;
		/**
		 * can be one of the following: recipe Id, collection Id, country code,
		 * username
		 * 
		 * pk-sk, gsi1-pk
		 */
		private String category;

		@DynamoDBAutoGeneratedKey
		@DynamoDBHashKey
		public String getPk() {
			return pk;
		}
		public void setPk(String pk) {
			this.pk = pk;
		}

		@DynamoDBIndexHashKey(globalSecondaryIndexName = "CategoryGsi")
		@DynamoDBRangeKey
		public String getCategory() {
			return category;
		}
		public void setCategory(String category) {
			this.category = category;
		}
	}

	@DynamoDBDocument
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Settings {
		private String title;
		private String description;

		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
	}

	@DynamoDBDocument
	@NoArgsConstructor
	@Getter
	@Setter
	@EqualsAndHashCode
	public static class DataMap {
		@EqualsAndHashCode.Exclude
		private String key;

		@EqualsAndHashCode.Exclude
		private String value;

		private String id;

		public DataMap(String id) {
			this.id = id;
		}
	}

	@DynamoDBDocument
	@NoArgsConstructor
	@AllArgsConstructor
	public static class PublicationSettings {
		private String country;
		private List<String> socialMedia;
		private String productRelation;
		private String collection;

		public String getCountry() {
			return country;
		}
		public void setCountry(String country) {
			this.country = country;
		}
		public List<String> getSocialMedia() {
			return socialMedia;
		}
		public void setSocialMedia(List<String> socialMedia) {
			this.socialMedia = socialMedia;
		}
		public String getProductRelation() {
			return productRelation;
		}
		public void setProductRelation(String productRelation) {
			this.productRelation = productRelation;
		}
		public String getCollection() {
			return collection;
		}
		public void setCollection(String collection) {
			this.collection = collection;
		}
	}

	public DataKey getKey() {
		return key;
	}

	public void setKey(DataKey key) {
		this.key = key;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public String getPreparation() {
		return preparation;
	}

	public void setPreparation(String preparation) {
		this.preparation = preparation;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public List<PublicationSettings> getPublications() {
		return publications;
	}

	public void setPublications(List<PublicationSettings> publications) {
		this.publications = publications;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public String getPostedOn() {
		return postedOn;
	}

	public void setPostedOn(String postedOn) {
		this.postedOn = postedOn;
	}

	public String getScheduledOn() {
		return scheduledOn;
	}

	public void setScheduledOn(String scheduledOn) {
		this.scheduledOn = scheduledOn;
	}

	public Settings getSeoSettings() {
		return seoSettings;
	}

	public void setSeoSettings(Settings seoSettings) {
		this.seoSettings = seoSettings;
	}

	public Settings getOpenGraphSettings() {
		return openGraphSettings;
	}

	public void setOpenGraphSettings(Settings openGraphSettings) {
		this.openGraphSettings = openGraphSettings;
	}

	@DynamoDBHashKey(attributeName = "pk")
	@DynamoDBAutoGeneratedKey
	public String getPk() {
		return key != null ? key.getPk() : null;
	}

	public void setPk(String pk) {
		if (key == null) {
			key = new DataKey();
		}
		this.key.setPk(pk);
	}

	@DynamoDBRangeKey(attributeName = "category")
	public String getCategory() {
		return key != null ? key.getCategory() : null;
	}

	public void setCategory(String category) {
		if (key == null) {
			key = new DataKey();
		}
		this.key.setCategory(category);
	}

	public void setRecipeId(String recipeId) {
		this.setCategory(Constants.RECIPE_PREFIX + recipeId);
	}

	@DynamoDBIgnore
	public String getRecipeId() {
		if (this.getCategory() != null && this.getCategory().startsWith(Constants.RECIPE_PREFIX)) {
			return this.getCategory().substring(2);
		}
		return this.getCategory();
	}
	public void setCollectionId(String collectionId) {
		this.setCategory(Constants.COLLECTION_PREFIX + collectionId);
	}

	@DynamoDBIgnore
	public String getCollectionId() {
		if (this.getCategory() != null && this.getCategory().startsWith(Constants.COLLECTION_PREFIX)) {
			return this.getCategory().substring(2);
		}
		return this.getCategory();
	}

	public Float getRating() {
		return rating;
	}

	public void setRating(Float rating) {
		this.rating = rating;
	}

	public List<String> getCollections() {
		return collections;
	}

	public void setCollections(List<String> collections) {
		this.collections = collections;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<String> getCountries() {
		return countries;
	}

	public void setCountries(List<String> countries) {
		this.countries = countries;
	}

	public String getCountryGsiPk() {
		return countryGsiPk;
	}

	public void setCountryGsiPk(String countryGsiPk) {
		this.countryGsiPk = countryGsiPk;
	}
}
