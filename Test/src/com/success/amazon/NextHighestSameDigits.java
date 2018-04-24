package com.success.amazon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NextHighestSameDigits {

	public static void main(String[] args) {
		new NextHighestSameDigits().findNext(489);
	}
	
	private void findNext(int current){
		boolean flag = true;
		int next = current;
		while(flag){
			flag = match(current, ++next);
		}
	}
	
	private boolean match(Integer current, Integer next){
		List<Integer> currentSet = new ArrayList<>();
		List<Integer> nextSet = new ArrayList<>();
		
		for(Character currentStr : current.toString().toCharArray()){
			currentSet.add(Character.getNumericValue(currentStr));
		}
		for(Character nextStr : next.toString().toCharArray()){
			nextSet.add(Character.getNumericValue(nextStr));
		}
		Collections.sort(currentSet);
		Collections.sort(nextSet);
		if(currentSet.equals(nextSet)){
			System.out.println(" next number-->"+next);
			return false;
		}
		return true;
	}
}
