package com.success.leet.medium;

public class SmallestIntegerCannotBeRepresentedBySubSetInSet {

  static int find(int[] ip){
    
    int result = 1; //consider this is the smallest integer
    
    for(int i=0; i<ip.length && ip[i] <= result ; i++){
      result +=ip[i];
    }
    return result;
  }
  
  public static void main(String[] args) {
    System.out.println(find(new int[]{1,1,1,1}));
    System.out.println(find(new int[]{1,5,6}));
    System.out.println(find(new int[]{1,2,4,6}));
    System.out.println(find(new int[]{2,3,4,5}));
  }

}
