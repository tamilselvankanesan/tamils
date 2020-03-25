package com.oster.recipes.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.TransactionWriteRequest;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.oster.recipes.dtos.CollectionDto;
import com.oster.recipes.dtos.RecipeDto;
import com.oster.recipes.dtos.UserDto;
import com.oster.recipes.entities.dynamodb.Data;
import com.oster.recipes.repositories.dynamodb.DataRepository;
import com.oster.recipes.utils.Constants;
import com.oster.recipes.utils.DataMapper;
import com.oster.recipes.utils.DateUtils;
import com.oster.recipes.utils.JwtUtil;
import com.oster.recipes.utils.Messages;
import com.oster.recipes.utils.Result;

@Service
@Transactional
public class RecipeService {

	@Autowired
	private DataRepository repo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private DataMapper mapper;

	@Autowired
	private DynamoDBMapper dynamoDbMmapper;
	private static final String RANGE_KEY="category";
	private static final String CATEGORY_INDEX_NAME="CategoryGsi";

	public Result<String> addCountries(List<Map<String, String>> countries) {
		List<Data> entities = new ArrayList<>();
		countries.forEach(e->{
			e.entrySet().forEach(entry->{
				if(StringUtils.isNoneBlank(entry.getKey(), entry.getValue())) {
					Data entity = new Data(Constants.COUNTRY_PK, entry.getKey().trim().toUpperCase());
					entity.setCategoryName(entry.getValue().trim());
					entities.add(entity);
				}
			});
		});
		
		if(!entities.isEmpty()) {
			dynamoDbMmapper.batchSave(entities);
		}
		return new Result<String>(true, Messages.COUNTRY_CREATE_SUCCESS);
	}
	
	public Result<UserDto> addUser(UserDto dto) {
		Result<UserDto> response = null;
		if(StringUtils.isBlank(dto.getUserName())) {
			return new Result<UserDto>(Messages.USER_NAME_REQUIRED);
		}
		Data entity = findUserByUsername(dto);
		
		if(entity != null) {
			response = new Result<UserDto>(Messages.USER_EXISTS);
		}else {
			entity = mapper.fromUserDto(dto);
			entity.setCategory(dto.getUserName().toLowerCase().trim());
			entity.setPassword(passwordEncoder.encode(dto.getPassword()));
			repo.save(entity);
			dto.setPk(entity.getPk());
			response = new Result<UserDto>(true, Messages.USER_CREATE_SUCCESS, dto);
		}
		return response;
	}
	
	public Result<UserDto> login(String userName, String password, HttpServletResponse response){
		UserDto dto = new UserDto();
		dto.setUserName(userName);
		dto.setPassword(password);
		if(StringUtils.isBlank(dto.getUserName())) {
			return new Result<UserDto>(Messages.USER_NAME_REQUIRED);
		}
		Data entity = findUserByUsername(dto);
		if(entity==null) {
			return new Result<UserDto>(Messages.INVALID_CREDENTIALS);
		}
		if(passwordEncoder.matches(dto.getPassword(), entity.getPassword())) {
			dto = mapper.toUserDto(entity);
			jwtUtil.setTokenHeader(jwtUtil.generateAccessToken(dto), response);
			return new Result<UserDto>(true, Messages.LOGIN_SUCCESS, dto);
		}else {
			return new Result<UserDto>(Messages.INVALID_CREDENTIALS);
		}
	}
	
	private Data findUserByUsername(UserDto dto) {
		Map<String,AttributeValue> expressionAttributeValues = new HashMap<>();
	    expressionAttributeValues.put(":category",new AttributeValue().withS(dto.getUserName().toLowerCase().trim()));
		
		Data entity = new Data();
		entity.setCategory(dto.getUserName().toLowerCase().trim());
		DynamoDBQueryExpression<Data> ex = new DynamoDBQueryExpression<Data>()
				.withIndexName(CATEGORY_INDEX_NAME).withConsistentRead(false)
				.withKeyConditionExpression("category = :category")
				.withExpressionAttributeValues(expressionAttributeValues);
		List<Data> queryResults = dynamoDbMmapper.query(Data.class, ex);
		return queryResults.size()>0?queryResults.get(0):null;
	}

	public String saveRecipe() {
		return "success";
	}
	
	public Result<CollectionDto> createCollection(CollectionDto dto, HttpServletRequest request) {
		Result<CollectionDto> result = null;
		
		if(StringUtils.isBlank(dto.getName())) {
			return new Result<CollectionDto>(Messages.COLLECTION_NAME_REQUIRED);
		}
		
		boolean exists = false;
		Data entity = new Data();
		entity.setPk(getUserId(request));
		
		Condition rk = new Condition();
		rk.withComparisonOperator(ComparisonOperator.BEGINS_WITH)
				.withAttributeValueList(new AttributeValue().withS(Constants.COLLECTION_PREFIX));
		
		DynamoDBQueryExpression<Data> ex = new DynamoDBQueryExpression<Data>().withHashKeyValues(entity)
				.withRangeKeyCondition(RANGE_KEY, rk);
		/*
		 * Map<String, AttributeValue> collAttrValMap = new HashMap<>();
		 * collAttrValMap.put(":collName", new AttributeValue(dto.getName()));
		 * ex.setExpressionAttributeValues(collAttrValMap);
		 */
		
		List<Data> queryResults = dynamoDbMmapper.query(Data.class, ex);
		exists = queryResults.stream().anyMatch(r->dto.getName().trim().equalsIgnoreCase(r.getCategoryName().toLowerCase()));
		if(exists) {
			result = new Result<CollectionDto>(Messages.COLLECTION_EXISTS);
		}else {
			entity.setCategoryName(dto.getName().trim());
			entity.setCollectionId(UUID.randomUUID().toString());
			repo.save(entity);
			dto.setCollectionId(entity.getCollectionId());
			result = new Result<CollectionDto>(true, Messages.COLLECTION_CREATE_SUCCESS, dto);
		}
		return result;
	}

	public Result<RecipeDto> createOrUpdateRecipe(RecipeDto dto, HttpServletRequest request){
		if(StringUtils.isAnyBlank(dto.getTitle(), dto.getIngredients(), dto.getPreparation())) {
			return new Result<RecipeDto>(Messages.RECIPE_MANDATORY_FIELDS);
		}
		Data entity = mapper.fromRecipeDto(dto);
		entity.setPk(getUserId(request));
		if(StringUtils.isBlank(dto.getRecipeId())) {
			entity.setRecipeId(UUID.randomUUID().toString());
			entity.setCreatedOn(DateUtils.getCurrentDateString());	
		}
		repo.save(entity);
		return new Result<RecipeDto>(true, Messages.RECIPE_SAVE_SUCCESS, mapper.toRecipeDto(entity));
	}

	public Result<RecipeDto> getRecipe(String recipeId, HttpServletRequest request) {
		Data data = new Data();
		data.setPk(getUserId(request));
		data.setRecipeId(recipeId);
		Optional<Data> entity = repo.findById(data.getKey());
		if(entity.isPresent()) {
			return new Result<RecipeDto>(true, Messages.RECIPE_FOUND, mapper.toRecipeDto(entity.get()));
		}else {
			return new Result<RecipeDto>(Messages.RECIPE_NOT_FOUND);
		}
	}
	
	public Result<List<RecipeDto>> getRecipes(HttpServletRequest request, String collection, String country){
		List<Data> entities = getAllRecipes(request, collection, country);
		List<RecipeDto> dtos = entities.stream().map(mapper::toRecipeDto).collect(Collectors.toList());
		return new Result<List<RecipeDto>>(true, Messages.RECIPE_FOUND, dtos);
	}
	
	private List<Data> getAllRecipes(HttpServletRequest request, String collection, String country) {
		Data data = new Data();
		data.setPk(getUserId(request));
	    
	    Condition rk = new Condition();
		rk.withComparisonOperator(ComparisonOperator.BEGINS_WITH)
				.withAttributeValueList(new AttributeValue().withS(Constants.RECIPE_PREFIX));
		
		DynamoDBQueryExpression<Data> ex = new DynamoDBQueryExpression<Data>().withHashKeyValues(data)
				.withRangeKeyCondition(RANGE_KEY, rk);
		
		
		Map<String,AttributeValue> expressionAttributeValues = new HashMap<>();
		if(StringUtils.isNotBlank(collection)) {
			expressionAttributeValues.put(":collection",new AttributeValue().withS(collection.trim()));
			ex = ex.withFilterExpression("contains (collections, :collection)");
		}
		
		if(StringUtils.isNotBlank(country)) {
			expressionAttributeValues.put(":country",new AttributeValue().withS(country.trim()));
			ex = ex.withFilterExpression("contains (countries, :country)");
		}
	    
	    if(!expressionAttributeValues.isEmpty()) {
	    	ex = ex.withExpressionAttributeValues(expressionAttributeValues);
	    }
	    return dynamoDbMmapper.query(Data.class, ex);
	    
	}
	
	public Result<String> deleteRecipe(String recipeId, HttpServletRequest request){
		Data entity = new Data();
		entity.setRecipeId(recipeId);
		entity.setPk(getUserId(request));
		repo.delete(entity);
		return new Result<String>(true, Messages.RECIPE_DELETE_SUCCESS);
	}
	
	public Result<String> deleteCollection(String collection, HttpServletRequest request){
		//update all recipes that are part of this collection
		List<Data> entitiesToUpdate = getAllRecipes(request, collection, null);
		TransactionWriteRequest transactionWriteRequest = new TransactionWriteRequest();
		entitiesToUpdate.forEach(e -> {
			e.getCollections().remove(collection);
			transactionWriteRequest.addPut(e);
		});
		Data entityToDelete = new Data();
		entityToDelete.setPk(getUserId(request));
		entityToDelete.setCollectionId(collection.split("_")[0]);
		transactionWriteRequest.addDelete(entityToDelete);
		dynamoDbMmapper.transactionWrite(transactionWriteRequest);
		
		return new Result<String>(true, Messages.COLLECTION_DELETE_SUCCESS);
	}
	
	private String getUserId(HttpServletRequest request) {
		return jwtUtil.getClaimFromToken(request, Constants.USER_ID_PARAM);
	}
}
