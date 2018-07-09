package com.cmecf.util;

import java.util.concurrent.BlockingQueue;

import org.apache.log4j.Logger;

public class QueueProcessor extends Thread{
	private static final Logger logger = Logger.getLogger(QueueProcessor.class);
	private BlockingQueue<String> queue;
	public QueueProcessor(BlockingQueue<String> queue){
		this.queue = queue;
	}
	public void run(){
		logger.info("QueueProcessor is running....");
		String entryId;
		try {
			while((entryId = queue.take())!=null){
				logger.info("retrieved value from the queue -> "+entryId);
				processRules(entryId);
			}
		} catch (InterruptedException e) {
			logger.error("Queue interrupted.. restart thread?? ....", e);
			//restart thread ???
		}
	}
	private void processRules(String entryId){
		//call rules controller composite facade and process rules
		//when the process is successful, delete the entry from the DB
	}
}
