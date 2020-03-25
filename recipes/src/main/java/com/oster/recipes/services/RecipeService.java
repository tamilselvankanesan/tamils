package com.oster.recipes.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
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

import lombok.extern.log4j.Log4j;

@Service
@Log4j
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
		entity.setPk(jwtUtil.getClaimFromToken(request, Constants.USER_ID_PARAM));
		
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
		entity.setPk(jwtUtil.getClaimFromToken(request, Constants.USER_ID_PARAM));
		if(StringUtils.isBlank(dto.getRecipeId())) {
			entity.setRecipeId(UUID.randomUUID().toString());
			entity.setCreatedOn(DateUtils.getCurrentDateString());	
		}
		repo.save(entity);
		return new Result<RecipeDto>(true, Messages.RECIPE_SAVE_SUCCESS, mapper.toRecipeDto(entity));
	}
	
	public Result<String> deleteRecipe(String recipeId, HttpServletRequest request){
		Data entity = new Data();
		entity.setRecipeId(recipeId);
		entity.setPk(jwtUtil.getClaimFromToken(request, Constants.USER_ID_PARAM));
		repo.delete(entity);
		return new Result<String>(true, Messages.RECIPE_DELETE_SUCCESS);
	}
}
