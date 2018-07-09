package com.success.leet.simple;

public class RemoveDuplicateFromSortedArray {
  public static void main(String[] args) {
    System.out.println(removeDuplicates(new int[]{1,1,2,3}));
  }
  static int removeDuplicates(int[] nums){
    
    int j=0; //holds the final unique array index
    
    for(int i=0; i< nums.length-1; i++){
      if(nums[i] != nums[i+1]){
        //not duplicate
        nums[j++] = nums[i]; //set the value starting from index 0..
      }
    }
    nums[j++] = nums[nums.length-1]; // always consider the last element
    for(int i=0; i<nums.length; i++){
      System.out.print(nums[i]);      
    }
    return j;
  }
}
