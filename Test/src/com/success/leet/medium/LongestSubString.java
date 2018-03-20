package com.success.leet.medium;

import java.util.ArrayList;
import java.util.List;

public class LongestSubString {

  public static void main(String[] args) {
    System.out.println(lengthOfLongestSubstring("abcdeadefghijkkmna"));
    System.out.println(lengthOfLongestSubstring("abcabcbb"));
    System.out.println(lengthOfLongestSubstring("abcadefghi"));
    System.out.println(lengthOfLongestSubstring("bbbbb"));
    System.out.println(lengthOfLongestSubstring("pwwkew"));
    
    System.out.println(" -------- ");
    System.out.println(lengthOfLongestSubstringUsingList("abcdeadefghijkkmna"));
    System.out.println(lengthOfLongestSubstringUsingList("abcabcbb"));
    System.out.println(lengthOfLongestSubstringUsingList("abcadefghi"));
    System.out.println(lengthOfLongestSubstringUsingList("bbbbb"));
    System.out.println(lengthOfLongestSubstringUsingList("pwwkew"));
  }
  public static int lengthOfLongestSubstringUsingList(String s) {
    if("".equals(s)){
      return 0;
    }
    String[] ipArr = s.split("");
    if(ipArr.length ==1){
      return 1;
    }
    List<String> list = new ArrayList<>();
    int length = 0;
    //abcdeadefghijkkmna
    outer:
    for(int i=0; i<ipArr.length; i++){
      if(list.contains(ipArr[i])){
        int revisedIndex = list.indexOf(ipArr[i]) + 1;
        if(list.size() > length){
          length = list.size();
        }
        for(int j=revisedIndex-1; j>=0; j--){
          list.remove(j); //remove from the end.. otherwise use iterator...
        }
        list.add(ipArr[i]);
        continue outer;
      }
      list.add(ipArr[i]);
    }
    return list.size() > length?list.size():length;
  }
  /**
   * Given a string, find the length of the longest substring without repeating characters.
      Examples:
        Given "abcabcbb", the answer is "abc", which the length is 3.
        Given "bbbbb", the answer is "b", with the length of 1.
        Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, 
        "pwke" is a subsequence and not a substring.
   * @param s
   * @return
   */
  public static int lengthOfLongestSubstring(String s) {
    
    if("".equals(s)){
      return 0;
    }
    String[] ipArr = s.split("");
    if(ipArr.length ==1){
      return 1;
    }
    String[] tempArr = new String[ipArr.length];
    int tempArrIndex = 0;
    //aabbcdefghb
    //abcdeadefghijkkmna
    int count = 0;
    int length = 0;
    int itr = 0;
    //push each element from input array to temp array.. and increase the counter... before push compare the ip element in the temp array.. if not found push ..
    //if found then set the count in the length and adjust the start position for the temp array and also adjust the count...
    outer:
    for(int i=0; i<ipArr.length; i++){
      itr = 0;
      for(int j=tempArrIndex; j<i; j++){
        itr ++;
        if(ipArr[i].equals(tempArr[j])){
//          tempArr[j] = null;
          //match
          if(count > length){
            length = count;
          }
          tempArrIndex = j+1; //move the tempArry pointer...
          count = count - (itr);
          if(count <0){
            count = 0;
          }
          
          tempArr[i] =  ipArr[i];
          count ++;
          continue outer;
        }
      }
      tempArr[i] =  ipArr[i];
      count ++;
    }
    
    return count > length?count:length;
  }
}
