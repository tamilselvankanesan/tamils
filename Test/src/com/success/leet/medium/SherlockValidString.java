package com.success.leet.medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SherlockValidString {

  public static void main(String[] args) {
     System.out.println(solution("aabbc"));
     System.out.println(solution("aaabb"));
     System.out.println(solution("aabbccdd"));
     System.out.println(solution("aabbcdcd"));
    System.out.println(solution("aabbcdcda"));
     System.out.println(solution("aaabbcdcd"));
     System.out.println(solution("aaabbcdcdd"));
  }

  static String solution(String s) {

    Map<Character, Integer> map = new HashMap<>();

    for (Character c : s.toCharArray()) {
      if (map.containsKey(c)) {
        map.put(c, map.get(c) + 1);
      }
      else {
        map.put(c, 1);
      }
    }

    Set<Integer> set = new HashSet<>(map.values());
    if (set.size() == 1) {
      return "YES";
    }
    if (set.size() > 2) {
      return "NO";
    }
    else {

      int f1 = 0;
      int f2 = 0;
      int index = 0;
      for (Integer i : set) {
        if (index == 0) {
          index++;
          f1 = i;
        }
        else {
          f2 = i;
        }
      }

      int f1Count = 0;
      int f2Count = 0;

      for (int i : map.values()) {
        if (i == f1) {
          f1Count++;
        }
        else {
          f2Count++;
        }
      }
      if (f1Count == 1 && f2Count == 1 && (f1==1 || f2==1 ||  Math.abs(f2 - f1) == 1)) {
        //if only 2 set of characters present and either one of the characters freq is one or the freq difference is 1 - then consider valid
        return "YES";
      }

      if (f1Count > 1 && f2Count > 1) { //aabbcccccc
        return "NO";
      }
      if((f1Count ==1 && f1 ==1 ) ||(f2Count==1 && f2 ==1) || Math.abs(f1 - f2) == 1){
        return "YES";
      }
    }

    return "NO";
  }
}
