package com.cmecf.util;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;

public class RulesProcessor {
	private static final Logger logger = Logger.getLogger(RulesProcessor.class); 
	private final BlockingQueue<String> queue = new LinkedBlockingQueue<>();
	private static RulesProcessor rulesProcessor = new RulesProcessor();
	private RulesProcessor(){
		QueueProcessor queueProcessor = new QueueProcessor(queue);
		queueProcessor.start();
	}
	public static RulesProcessor getInstance(){
		return rulesProcessor;
	}
	public void addToRulesQueue(String entryId){
		try {
			logger.info("put it in the queue");
			//add to the DB and add to the queue
			queue.put(entryId);
		} catch (InterruptedException e) {
			logger.error(e);
		}
	}
	public void addToRulesQueue(String entryId, String caseId){
		try {
			logger.info("put it in the queue");
			//add to the DB and add to the queue
			queue.put(entryId);
		} catch (InterruptedException e) {
			logger.error(e);
		}
	}
	public void addToRulesQueue(String entryId, String caseId, String ripeMotionEntryId){
		try {
			logger.info("put it in the queue");
			//add to the DB and add to the queue
			queue.put(entryId);
		} catch (InterruptedException e) {
			logger.error(e);
		}
	}
}
