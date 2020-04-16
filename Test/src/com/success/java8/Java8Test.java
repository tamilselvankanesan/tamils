package com.success.java8;

import java.util.function.Function;

public class Java8Test {

	public static void main(String[] args) {

		Function<Integer, Integer> func = (x) -> {
			System.out.println("one");
			return x * 2;
		};
		Function<Integer, Integer> func1 = (x) -> x * 3;
		
		System.out.println(func.apply(10));
		System.out.println(func.andThen(func1).apply(20));

		System.out.println(func.compose(func1).apply(20));
		
	}

}
