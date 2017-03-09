package com.cmecf.services;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cmecf.util.RulesProcessor;

@RestController
@RequestMapping("/rules")
public class RulesController {

	@RequestMapping(produces={"application/json"}, method=RequestMethod.GET)
	public String addToQueue(@RequestParam(name="entryId") String entryId){
		RulesProcessor.getInstance().addToRulesQueue(entryId);
		return "added to the queue successfully";
	}
}
