package com.success.leet.simple;

public class NextGreaterElement1 {

	public static void main(String[] args) {
		int[] sub = new int[]{4,1,2}; int[] main = new int[]{5,3,4,2};
		nextGreaterLement(sub, main);
		System.out.println(" ");
		sub = new int[]{2,4}; main = new int[]{1,2,3,4};
		nextGreaterLement(sub, main);
	}

	private static int[] nextGreaterLement(int[] sub, int[] main) {
		/*
		 * Input: nums1 = [4,1,2], nums2 = [1,3,4,2]. Output: [-1,3,-1]
		 * Explanation: For number 4 in the first array, you cannot find the
		 * next greater number for it in the second array, so output -1. For
		 * number 1 in the first array, the next greater number for it in the
		 * second array is 3. For number 2 in the first array, there is no next
		 * greater number for it in the second array, so output -1.
		 */
//		String s  = "123";
//		System.out.println(Integer.parseInt(s.split("")[1]));
		int[] result = new int[sub.length];
		for (int i = 0; i < sub.length; i++) {
//			int mainIndex = -1;
			for (int j = i; j < main.length; j++) {

				if (/*mainIndex != -1 && */main[j] > sub[i]) {
					result[i] = main[j];
					break;
				}

				/*if (mainIndex == -1 && sub[i] == main[j]) {
					mainIndex = j;
				}*/
			}
			if(result[i] == 0){
				result[i] = -1;
			}
		}
		for(int k : result){
			System.out.print(k);
		}
		return result;
	}
}
