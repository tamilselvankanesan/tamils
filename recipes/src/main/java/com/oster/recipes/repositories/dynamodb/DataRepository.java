package com.oster.recipes.repositories.dynamodb;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import com.oster.recipes.entities.dynamodb.Data;
import com.oster.recipes.entities.dynamodb.Data3;
import com.oster.recipes.entities.dynamodb.Data3.DataKey;

@EnableScan
public interface DataRepository extends CrudRepository<Data, DataKey>{
		
	Data3 findByPk(String pk);
	
	Data3 findByCategory(String category);
	
	Data3 findByCategoryContains(String category);
}
