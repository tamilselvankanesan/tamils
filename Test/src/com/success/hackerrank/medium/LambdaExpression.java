package com.success.hackerrank.medium;

import java.io.*;
import java.util.*;

interface PerformOperation {
	boolean check(int a);
}

class MyMath {
	public static boolean checker(PerformOperation p, int num) {
		return p.check(num);
	}

	public static PerformOperation isOdd() {
		return (a) -> a % 2 != 0;
	}
	
	public static PerformOperation isOddRegular() {
		return new PerformOperation() {
			@Override
			public boolean check(int a) {
				return a%2!=0;
			}
		};
	}

	public static PerformOperation isPrime() {
		return (a) -> {
			for (int j = 2; j <= Math.sqrt(a); j++) {
				if (a % j == 0) {
					return false;
				}
			}
			return true;
		};
	}

	public static PerformOperation isPalindrome() {
		return (a) -> palindrome(a);
	}

	public static boolean palindrome(int input) {
		int inputTemp = input;
		boolean flag = false;
		int reversedNumber = 0;
		while (input != 0) {
			reversedNumber = reversedNumber * 10;
			reversedNumber = reversedNumber + input % 10;
			input = input / 10;
		}
		if (inputTemp == reversedNumber) {
			flag = true;
		}
		return flag;
	}

}

public class LambdaExpression {

	public static void main(String[] args) throws IOException {
		MyMath ob = new MyMath();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		PerformOperation op;
		boolean ret = false;
		String ans = null;
		while (T-- > 0) {
			String s = br.readLine().trim();
			StringTokenizer st = new StringTokenizer(s);
			int ch = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			if (ch == 1) {
				op = ob.isOdd();
				ret = ob.checker(op, num);
				ans = (ret) ? "ODD" : "EVEN";
			} else if (ch == 2) {
				op = ob.isPrime();
				ret = ob.checker(op, num);
				ans = (ret) ? "PRIME" : "COMPOSITE";
			} else if (ch == 3) {
				op = ob.isPalindrome();
				ret = ob.checker(op, num);
				ans = (ret) ? "PALINDROME" : "NOT PALINDROME";

			}
			System.out.println(ans);
		}
	}
}
