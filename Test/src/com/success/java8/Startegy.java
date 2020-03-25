package com.success.java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Startegy {

	
	private static int calcualte(Predicate<Integer> cond, List<Integer> input) {
		return input.stream().filter(cond).mapToInt(e->e).sum();
	}
	
	public static void main(String[] args) {
		Predicate<Integer> totalCond = num -> true;
		Predicate<Integer> oddTotalCond = num -> num%2!=0;
		Predicate<Integer> evenTotalCond = num -> num%2==0;
		List<Integer> input = Arrays.asList(1,2,3,4,5,6,7);
		System.out.println(calcualte(totalCond, input));
		System.out.println(calcualte(oddTotalCond, input));
		System.out.println(calcualte(evenTotalCond, input));
	}

}
