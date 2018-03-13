package com.success.leet.simple;

public class BinarySearch {

  public static void main(String[] args) {
    System.out.println(search(new int[]{1,7,8,9,28,30,50,70,76}, 70));
  }
  private static int search(int[] input, int target){
    /*
     * 1. find the middle index 
     * 2. check if the middle index is > target
     * 3. if true then set high = middle-1 index 
     *    3.1 go to step 1 to 4
     * 4. if middle index is < target false then set low = middle+1
     *    4.1 go to step 1 to 4
     * 5. if middle index is = then return
     * 6. continue until low > high
     * 
     * 1. mid = 4, low = 4 high = 8
     * 2. mid = 4 + 2 = 6
     */
    
    int low = 0;
    int high = input.length -1;
    while(low<high){
      int middle = low + (high-low)/2;
      if(input[middle] == target){
        return middle;
      }
      if(input[middle] > target){
        high = middle-1;//middle - 1 because we have already checked the middle. so reduce 1 from high index
      }else if(input[middle] < target){
        low = middle+1; //middle + 1 because we have already checked the middle, so increase 1 to the low index 
      }
    }
    return -1;
  }
}
