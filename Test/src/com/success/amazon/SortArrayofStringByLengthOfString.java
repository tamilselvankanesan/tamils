package com.success.amazon;

import java.util.Arrays;
import java.util.Comparator;

public class SortArrayofStringByLengthOfString {
	
	private static void sort(String[] source){
		for(int i=0; i<source.length; i++){
			for(int j=i+1; j<source.length; j++){
				if(source[i].length()>source[j].length() || 
						(source[i].length()==source[j].length() && source[i].compareTo(source[j])>0)){
					String temp = source[i];
					source[i] = source[j];
					source[j] = temp;
				}
			}
		}
		for(String ss: source){
			System.out.println(ss);
		}
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] arr = new String[]{"hello","abc","Testing","maths","manne"};
		Arrays.sort(arr,new MyComp());
		for(String ss: arr){
			System.out.println(ss);
		}
		sort(arr);
	}
	static class MyComp implements Comparator<String>{

		@Override
		public int compare(String o1, String o2) {
			if(o1.length()>o2.length()){
				return 1;
			}else if(o1.length()<o2.length()){
				return -1;
			}
			return o1.compareTo(o2);
		}
		
	}
}
