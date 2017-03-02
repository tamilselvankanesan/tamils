package com.success.programs;

import java.util.ArrayList;
import java.util.List;

public class FriendFinder {
	private String nameToSearch = null;
	private int length;
	public FriendFinder(String nameToSearch){
		this.nameToSearch = nameToSearch;
	}
	private int findFriends(int level, String name){
		
		for(String fName : getFriendsList(name)){
			
			if(nameToSearch.equals(fName)){
				return level;
			}else{
				findFriends(length++, name);
			}
		}
		return 0;
	}
	
	public static void main(String[] args) {
		new FriendFinder("Tamil").findFriends(0, "Selvan");
	}
	
	private List<String> getFriendsList(String name){
		List<String> friendsList = new ArrayList<>();
		friendsList.add("Relvan");
		friendsList.add("Tamil");
		friendsList.add("selvan");
		
		return friendsList;
	}
}
