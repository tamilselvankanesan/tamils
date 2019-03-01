package com.success.sqs;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;

public class LambdaSQSHandler implements RequestHandler<SQSEvent, String> {

	@Override
    public String handleRequest(SQSEvent event, Context context) {
        context.getLogger().log("Input received inside handler.... ");
        
        if(event.getRecords()!=null){
        	event.getRecords().forEach(e -> {
        		context.getLogger().log("The message ID is... "+e.getMessageId());
        	});
        }
        return "Hello from Lambda!";
    }
}
