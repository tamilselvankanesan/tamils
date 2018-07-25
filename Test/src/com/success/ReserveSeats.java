package com.success;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;


public class ReserveSeats {

	/**
	 * Key is row number .. value List<Integer> is the partition information
	 */
	private Map<Integer, List<Integer>> layoutMap = new HashMap<>();
	/**
	 * Key is number of tickets.. value List<String> is requestor's name
	 */
	private Map<Integer, List<String>> ticketsRequestMap = new HashMap<>();
	
	public static void main(String[] args) {
		ReserveSeats rs = new ReserveSeats();
		rs.compute();
	}

	private void compute(){
		try {
			System.out.println("enter input ... ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			
			int emptyLines = 0;
			while(emptyLines < 2){
				String line = reader.readLine();
				if(line== null || "".equals(line.trim())){
					emptyLines++;
				}else if(emptyLines == 0){
					//read layout
					readLayout(line);
				}else{
					//read tickets request
					readTicketsRequest(line);
				}
			}
			reader.close();
			System.out.println(" .... All lines are read ... ");
			assignSeats();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void readLayout(String line){
		String[] array = line.split(" +");
		int layoutMapSize = layoutMap.size() + 1;
		for(String partition : array){
			layoutMap.computeIfAbsent(layoutMapSize, layoutList -> new ArrayList<>()).add(Integer.parseInt(partition));
		}
	}
	
	private void readTicketsRequest(String line){
		String[] array = line.split(" +");
		if(array.length !=2 && !array[1].chars().allMatch(Character::isDigit)){
			//invalid request
			return;
		}
		ticketsRequestMap.computeIfAbsent(Integer.parseInt(array[1]), requestList -> new ArrayList<>()).add(array[0]);
	}
	private void assignSeats(){
		if(layoutMap.isEmpty() || ticketsRequestMap.isEmpty()){
			//no action
			return;
		}
		for(Entry<Integer, List<Integer>> entry : layoutMap.entrySet()){
			
			for(Integer partition : entry.getValue()){

				fill(entry.getKey(), partition);
				
			}
			
		}
	}
	private void fill(Integer row, Integer availableSeats){
		while(availableSeats > 0){
			
			if(ticketsRequestMap.get(availableSeats) != null && !ticketsRequestMap.get(availableSeats).isEmpty()){
				System.out.println("Row "+row +" is assigned to "+ticketsRequestMap.get(availableSeats).get(0));
				ticketsRequestMap.get(availableSeats).remove(0);
				
				availableSeats = 0 ;
			}else{
				availableSeats -- ;
			}
		}
	}
}
