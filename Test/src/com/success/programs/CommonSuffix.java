package com.success.programs;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CommonSuffix {

	private static String findMaxCommonSuffix(String one, String two) {
		
		int oneLength = one.length();
		int twoLength = two.length();
		
		String[][] arr = new String[oneLength][twoLength];
		String resultString = "";
		//for each character in the first string (incrementally), check if there is a match in other string (incrementally)
		for(int i=0; i<oneLength; i++) {
		
			for(int j=0; j<twoLength; j++) {
				
				if(one.charAt(i) == two.charAt(j)) {
					
					if(i == 0 || j==0) {
						//first character. the count is going to be 1 default. 
						arr[i][j] = String.valueOf(one.charAt(i));
					}else {
						//match.. append the previous character match(already captured the previous character match in both strings).
						arr[i][j] = arr[i-1][j-1] + String.valueOf(one.charAt(i));
						
					}
					if(resultString.length() < arr[i][j].length()) {
						//if the previous result is smaller than this, update the result.
						resultString = arr[i][j];
					}
					
				}else {
					arr[i][j] = "";
				}
				
			}
			
		}
		return resultString.length() == 0? "NULL": resultString;
	}
	
	
	public static void main(String[] args) {
		InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);
        String line;
        try {
        	System.out.println("Enter an exactly one comma separated string:");
        	while (!(line = br.readLine()).trim().equals("")){
        		String[] arr = line.split(",");
        		if(arr.length !=2) {
        			System.out.println("Invalid string.");
        			continue;
        		}
        		String result = findMaxCommonSuffix(arr[0].trim(), arr[1].trim());
        		System.out.println("Result is "+result);
            }
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

}
