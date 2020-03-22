package com.oster.recipes.services;

import java.util.ArrayList;
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
import com.oster.recipes.dtos.UserDto;
import com.oster.recipes.entities.dynamodb.Data;
import com.oster.recipes.entities.dynamodb.Data3;
import com.oster.recipes.repositories.dynamodb.DataRepository;
import com.oster.recipes.utils.Constants;
import com.oster.recipes.utils.DataMapper;
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
			entity = mapper.toData(dto);
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
			dto = mapper.toDto(entity);
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

	public UserDto getUser(String userName) {
		repo.findAll().forEach(c -> log.info(c.getPk()));
		Data3 entity = repo.findByCategory(userName);
		if (entity != null) {
			log.info(entity.getPk());
//			
			DynamoDBQueryExpression<Data3> exp = new DynamoDBQueryExpression<>();
//			exp = exp.withKeyConditionExpression("begins_with(category, :cat)");

//			Map<String, Condition> cMap = new HashMap<>();
//			exp.withQueryFilter(cMap);

			Condition pc = new Condition();
			pc.setComparisonOperator(ComparisonOperator.EQ);
			List<AttributeValue> aList = new ArrayList<>();
			aList.add(new AttributeValue("a639b63d-455d-4919-b1a7-522066c786fb"));
			pc.setAttributeValueList(aList);
//			cMap.put("pk", pc);

			Condition rc = new Condition();
			rc.setComparisonOperator(ComparisonOperator.CONTAINS);
			aList = new ArrayList<>();
			aList.add(new AttributeValue("tam"));
			pc.setAttributeValueList(aList);
//			cMap.put("pk", rc);

//			exp = exp.withKeyConditionExpression("pk =:pk and begins_with(category, :cat)");
			exp = exp.withKeyConditionExpression("category =:cat ");

			Condition c = new Condition();
			c.setComparisonOperator(ComparisonOperator.BEGINS_WITH);
			aList = new ArrayList<>();
			aList.add(new AttributeValue("u-"));
			c.setAttributeValueList(aList);
//			exp = exp.withRangeKeyCondition("category", c);
//					.withRangeKeyCondition("category", "begins_with(category, :cat)");
			Map<String, AttributeValue> valuesMap = new HashMap<>();
//			valuesMap.put(":pk", new AttributeValue("a639b63d-455d-4919-b1a7-522066c786fb"));
			valuesMap.put(":cat", new AttributeValue("tamil"));
			exp = exp.withExpressionAttributeValues(valuesMap);

//			List<Data> d = dynamoDbMmapper.query(Data.class, exp);
//			log.info("size "+d.size());

			Data3 input = new Data3();
			input.setPk("a639b63d-455d-4919-b1a7-522066c786fb");

			Condition rk = new Condition();
			
			rk.withComparisonOperator(ComparisonOperator.BEGINS_WITH)
					.withAttributeValueList(new AttributeValue().withS("tami"));
			
			DynamoDBQueryExpression<Data3> ex = new DynamoDBQueryExpression<Data3>().withHashKeyValues(input)
					.withRangeKeyCondition("category", rk);

			List<Data3> d = dynamoDbMmapper.query(Data3.class, ex);
			log.info("size " + d.size());
		}

		return null;
	}
}
