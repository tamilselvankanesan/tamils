package awslamda.functions;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

import awslamda.models.Country;

public class MyFirstFunction{

	public String handleRequest(Country country, Context context) {
		LambdaLogger logger = context.getLogger();
		logger.log("input received from SNS is "+country.toString());
		System.out.println("Hello world.. this is my first lamda..");
		return String.valueOf(System.currentTimeMillis());
	}

}
