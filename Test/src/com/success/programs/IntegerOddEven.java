package com.success.programs;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class IntegerOddEven {

	private static boolean isNumber(String input) {
		try {
			Integer.parseInt(input.trim());
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	private static String manipulate(String input) {
		
		if(!isNumber(input)) {
			return "";
		}
		if(input.trim().length() == 1) {
			return input;
		}
		
		StringBuilder output = new StringBuilder();
		char[] arr = input.trim().toCharArray();
		for(int i=0; i< arr.length-1; i++) {
			if(arr[i]-48 == 0) {
				//skip 0
				output.append(arr[i]);
				continue;
			}
			int first = arr[i];
			int second = arr[i+1];
			if((first%2)==0 && (second%2)==0) {
				//even
				output.append(arr[i]);
				output.append("*");
			}else if((first%2)!=0 && (second%2)!=0) {
				//odd
				output.append(arr[i]);
				output.append("-");
			}else {
				output.append(arr[i]);
			}
		}
		//append the last digit
		output.append(arr[arr.length-1]);
		return output.toString();
	}
	
	public static void main(String[] args) {
		
		InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);
        String line;
        try {
        	System.out.println("Enter a positive integer:");
        	while (!(line = br.readLine()).trim().equals("")){
        		String result = manipulate(line);
        		if(result.isEmpty()) {
        			System.out.println("not a valid integer");	
        		}else {
        			System.out.println("output "+manipulate(line));
        		}
            	
            }
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
        
	}
}
