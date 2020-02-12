package com.success.hackerrank.medium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class LambdaExpressions {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String line = null;
		System.out.println("enter input....");
		while((line=reader.readLine())!=null && !"".equals(line.trim())) {
//			System.out.println(line);
			String[] arr = line.split(" ");
			switch (arr[0]) {
			case "1":
				
				perform(num -> num%2==0,
						print -> System.out.println(print),
						Integer.parseInt(arr[1].trim()),
						"EVEN");
				break;

			case "2":
				perform(num -> num%2!=0,
						print -> System.out.println(print),
						Integer.parseInt(arr[1].trim()),
						"ODD");
				break;	
			default:
				break;
			}
		}
	}
	
	public static void someFunction() {
		Function<String, String> someString = s -> "Welcome to my function. "+s;
		someString.apply("Tamil");
		
		Predicate<Integer> somePredicate = n -> n>0;
		System.out.println(somePredicate.test(1));
		
		Consumer<String> c = str -> System.out.println(str);
		c.accept("hello consumer");
		
		PerformOperation op = n -> n%2==0;
		System.out.println(op.check(2));
		System.out.println(op.checker(3));
		
		Arrays.asList(1,2,3,4,5).forEach(System.out::println);
		
		//stream
		// source, pipeline, aggregate, terminal... sequence and parallel streams
		
	}
	interface PerformOperation {
		 boolean check(int a);
		 default boolean checker(int a) {
			 return a%2==0;
		 }
		 static void another() {
			 
		 }
	}
	private static PerformOperation isPrime() {
		return (a) -> {
			for(int j=2;j<=Math.sqrt(a); j++){
				if(a%j==0){
					return false;
				}
			}
			return true;
		};
	}
	
	private static PerformOperation isOdd() {
		return (a) -> a%2!=0;
	}
	
	private static PerformOperation isPalindrome() {
		return (a) -> palindrome(a);
	}
	
	private static boolean palindrome(int input){
		int inputTemp = input ;
		boolean flag = true;
		int reversedNumber = 0;
		while(input!=0){
			reversedNumber = reversedNumber*10;
			reversedNumber = reversedNumber + input%10;
			input = input/10;
		}
		if(inputTemp == reversedNumber){
			flag = false;
		}
		return flag;
	}
	
	private static void perform(Predicate<Integer> condition, Consumer<String> consumer, Integer input, String printString) {
		if(condition.test(input)) {
			consumer.accept(printString);
		}
	}
	
	private static void perform2(Consumer<String> consumer, String printString) {
		consumer.accept(printString);
	}
	
	private static void perform1(Function<Integer, String> f, Integer input) {
		f.apply(input);
	}

}
