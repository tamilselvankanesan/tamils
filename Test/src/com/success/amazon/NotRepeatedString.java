package com.success.amazon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class NotRepeatedString {
	
	private void find (){
		String str = "sscceerss";
		char[]  arr = str.toCharArray();
		
		boolean repeat = false;
		for(int i=0; i<arr.length; i++){
			repeat = false;
			char c1 = arr[i];
			for(int j=0; j<arr.length; j++){
				if(i!=j && c1==arr[j]){
					repeat = true;
					break;
				}
			}
			if(!repeat){
				System.out.println(arr[i]);
				break;
			}
		}
		findUsingHashMap();
	}
	private void findUsingHashMap(){
		String str = "suucceessx";
		Map<Character, Integer> charMap = new HashMap<Character, Integer>();
		
		char[]  arr = str.toCharArray();
		for(Character c : arr){
			if(charMap.containsKey(c)){
				charMap.put(c, charMap.get(c)+1);
			}else{
				charMap.put(c, 1);
			}
		}
		for(Character c : arr){
			if(charMap.get(c)==1){
				System.out.println(c);
				break;
			}
		}
		findUsingIndex();
	}
	private void findUsingIndex(){
		String str = "suuceessg";
		for(char c : str.toCharArray()){
			if(str.indexOf(c) == str.lastIndexOf(c)){
				System.out.println(c);
				break;
			}
		}
	}
		
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new NotRepeatedString().find();
		Vector<String> ss = new Vector<>();
		for(int i=0; i<15;i++){
			ss.add("11");
		}
		ArrayList<String> sss = new ArrayList<>();
		for(int i=0; i<18;i++){
			sss.add("11");
		}
	}

}
