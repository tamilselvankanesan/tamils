package com.success.leet.simple;

public class FindSmalletMaximumFromTarget {

  public static void main(String[] args) {
    System.out.println(findSmallestMaximum(new int[]{1,7,8,9,28,30,50,70}, 8));
  }
  
  private static int findSmallestMaximum(int[] input, int target){
    
    int low = 0; 
    int high = input.length - 1;
    if(input[0] > target || input[high] < target){
      return 0;
    }
    int middle = 0;
    while(low < high){
      middle = low + (high - low)/2;
      /*if(input[middle]<target && input[middle+1] >target ){
        return middle+1;
      }*/
      if(input[middle] == target){
        return middle+1;
      }
      if(input[middle] > target){
        high = middle -1;
      }else if(input[middle] < target){
        low = middle +1;
      }
    }
    return middle;
  }
}
