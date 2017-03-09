package com.cmecf.util;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;

public class RulesProcessor {
	private static final Logger logger = Logger.getLogger(RulesProcessor.class); 
	private final BlockingQueue<String> queue = new LinkedBlockingQueue<>();
	private static RulesProcessor rulesProcessor = new RulesProcessor();
	private RulesProcessor(){
		logger.info("Initialize RulesProcessor....");
		loadQueueFromDatabase();
		QueueProcessor queueProcessor = new QueueProcessor(queue);
		queueProcessor.start();
		logger.info("RulesProcessor initialization successful");
	}
	private void loadQueueFromDatabase(){
		logger.info("loading persisted queue from DB");
		//read persisted queue from DB and add it to queue before starting the thread.
	}
	public static RulesProcessor getInstance(){
		return rulesProcessor;
	}
	public synchronized void addToRulesQueue(String entryId){
		try {
			logger.info("put it in the queue");
			//add to the DB and add to the queue
			queue.put(entryId);
		} catch (InterruptedException e) {
			logger.error(e);
		}
	}
	public synchronized void addToRulesQueue(String entryId, String caseId){
		try {
			logger.info("put it in the queue");
			//add to the DB and add to the queue
			queue.put(entryId);
		} catch (InterruptedException e) {
			logger.error(e);
		}
	}
	public synchronized void addToRulesQueue(String entryId, String caseId, String ripeMotionEntryId){
		try {
			logger.info("put it in the queue");
			//add to the DB and add to the queue
			queue.put(entryId);
		} catch (InterruptedException e) {
			logger.error(e);
		}
	}
}
