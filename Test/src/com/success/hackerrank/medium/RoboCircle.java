package com.success.hackerrank.medium;

import java.util.Arrays;

public class RoboCircle {

  static boolean isCircle(String command){
    char[] arr = command.toCharArray();
    
    int dir = 0;
    int x =0, y =0;
    
    for(char c:arr){
    
      //consider 4 quads (
      
      /*       N
       *       | 0
       *    3  |
       *   W___ ____ E
       *       |  
       *    2  |  1
       *       S
       * 
       * 
       * 
       */
      //if the command has no step , then it will never leave the circle
      //if the command has a step and if the robo is facing North then increment y, 
      //if the command has a step and if the robo is facing east then increment x
      //if the command has a step and if the robo is facing south then decrement y
      //if the command has a step and if the robo is facing west then decrement x
      
      if(c == 'R'){
        dir = (dir+1)%4;
      }else if(c=='L'){
        dir = ((4 + dir)-1)%4;
      }else{
        
        if(dir == 0){
          //facing north -> increment y
          y ++;
        }else if(dir == 1){
          //facing east -> increment x
           x++;
        }else if(dir == 2){
          //facing South -> decrement y
          y --;
        }else if(dir == 3){
          //facing west -> decrement x
          x --;
        }
      }
    }
    return (x==0 && y==0);
  }
  
  
  public static void main(String[] args) {
    for(String command : Arrays.asList(new String[]{"RRRRRRR","RGRRRR","LLLLLL","LGGGGGLLLL","LGLGLGLG","RGRGRGGRR","LGRGLGRG","LGLGLGLG"})){
      boolean flag = isCircle(command);
      if(flag){
        System.out.println("command "+command+" is circle");
      }else{
        System.out.println("command "+command+" is not circle");
      }   
    }
  }
}
