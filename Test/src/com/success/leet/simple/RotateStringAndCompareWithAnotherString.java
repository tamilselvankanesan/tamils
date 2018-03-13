package com.success.leet.simple;

public class RotateStringAndCompareWithAnotherString {

  public static void main(String[] args) {
    System.out.println(rotate("abcde", "cdeab"));
    System.out.println(rotate1("abcde", "cdeab"));
    
    System.out.println(rotate("abcde", "cdeax"));
    System.out.println(rotate1("abcde", "cdeax"));
  }
  private static boolean rotate(String A, String B){
    if(A.length()!=B.length()){
      return false;
    }
    int length = A.length();
    //total time complexity -> O(N^2) -> because for each iteration, we check upto N elements in A and B. space complexity -> O(1) - we only use pointers, no new storage required
    outer:
    for(int i=0; i<length; i++){
      for(int j=0; j<length;j++){
        if(A.charAt((i+j)%A.length())!=B.charAt(j)){
          //first iteration -> A -> 0,1,2,3,4 to B -> 0,1,2,3,4
          //second iteration -> A -> 1,2,3,4,0 to B -> 0,1,2,3,4
          //third iteration -> A -> 2,3,4,0,1 to B -> 0,1,2,3,4
          continue outer;
        }
      }
      return true;
    }
    return false;
  }
  
  private static boolean rotate1(String A, String B){
    if(A.length()==B.length() && (A+A).contains(B)){
      //concat A twice and check if the resulting string contains B
      //Space -> O(N).. need more memory to store A twice ..  
      return true;
    }
    return false;
  }
}
