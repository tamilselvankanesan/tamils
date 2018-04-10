package com.success.hackerrank.medium;

public class HighestValuePalindrome {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    System.out.println("ip: 092282 "+highestValuePalindrome("092282", 6, 3));
//    System.out.println("ip: 092282 "+highestValuePalindrome("3943", 4, 1));
//    System.out.println("ip: 0011 "+highestValuePalindrome("0011", 4, 1));
  }

  /*
   * Complete the highestValuePalindrome function below.
   */
  static String highestValuePalindrome(String s, int n, int k) {
    /*
     * Write your code here.
     */
    int left = 0;
    int right = n-1;
    char[] arr = s.toCharArray();
    char[] result = s.toCharArray();
    while(left < right){
      if(result[left] != result[right]){
        if(result[left] < result[right]){
          result[left] = result[right]; //set the maximum
        }
        if(result[left] > result[right]){
          result[right] = result[left]; //set the maximum
        }
        k--;
      }
      right --;
      left ++;
    }
    if(k<0){
      System.out.println("-1");
      return "-1";
    }
    if(k>0){
      left = 0;
      right = n-1;
      while(left <= right){
        if(k==1 && left==right && result[left]!='9'){
          result[left]='9';
          k--;
          break;
        }
        
        if(k>=2 && result[left] == arr[left] && arr[left]!='9' && arr[right] == result[right] && arr[right]!='9'){
          result[left] = result[right] = '9';
          k-=2;
        }
        
        if(k>=1 && (result[left]!=arr[left] || result[right]!=arr[right])){
          result[left] = result[right] = '9';
          k--;
        }
        left++; 
        right --;
      }  
    }
    
    
    System.out.println("IP ->"+new String(arr));
    System.out.println("OP ->"+new String(result));
    return new String(result);
  }
}
