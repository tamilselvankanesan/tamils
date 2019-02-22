package com.success.jbossrest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

@Service
public class HelloService {
	@Autowired
	private JdbcTemplate jt;
	
	public String someMethod(){
		StringBuilder result = new StringBuilder();
		SqlRowSet rowSet = jt.queryForRowSet("select * from country");
		while(rowSet.next()){
			result.append(rowSet.getString(2));
			result.append("\n");
		}
		return result.toString();
	}
}
