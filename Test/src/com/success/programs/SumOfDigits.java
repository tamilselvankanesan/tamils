package com.success.programs;

public class SumOfDigits {

	private int add(int number, int sum){
		if(number>0){
			sum = sum+number%10;
			number = number/10;
			sum = add(number, sum);
		}
		return sum;
	}
	public static void main(String[] args) {
		System.out.println(new SumOfDigits().add(1244, 0));
	}
}
