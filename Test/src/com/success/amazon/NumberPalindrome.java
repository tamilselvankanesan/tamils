package com.success.amazon;

public class NumberPalindrome {

	public static void main(String[] args) {
		new NumberPalindrome().palindrome(111);
	}
	private void palindrome(int input){
		int nextNumber, nextNumberTemp = input ;
		boolean flag = true;
		int reversedNumber = 0;
		while(flag){
			++nextNumberTemp; 
			nextNumber = nextNumberTemp;
			reversedNumber = 0;
			while(nextNumber!=0){
				reversedNumber = reversedNumber*10;
				reversedNumber = reversedNumber + nextNumber%10;
				nextNumber = nextNumber/10;
			}
			if(nextNumberTemp == reversedNumber){
				flag = false;
			}
		}
		System.out.println(nextNumberTemp+"");
	}
}
