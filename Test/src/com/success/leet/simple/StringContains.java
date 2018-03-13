package com.success.leet.simple;

public class StringContains {

  public static void main(String[] args) {
    System.out.println(contains("hello","llo"));
  }
  private static boolean contains(String A, String B){
    //Space complexity -> O(1) because no new space.... time complexity -> O(N^2) 
    //first iteration -> A -> 0,1
    //2nd iteration -> A -> 1,2
    //3rd iteration -> A -> 2,3
    boolean equal = true;
    for(int i=0; i<=A.length()-B.length(); i++){
      //always iterate A for A.length - B.length times only. because in the inner loop A will be iterated again
      equal = true;  
      for(int k=0; k<B.length(); k++){
        if(A.charAt(i+k) != B.charAt(k)){
          equal = false;
          break;
        }
      }
      if(equal){
        return true;
      }
    }
    
    return false;
  }
}
