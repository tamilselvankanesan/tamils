package com.success.amazon;

public class SubString{
	private String givenString = null;
	public SubString(String givenString){
		this.givenString = givenString; 
	}
 public static void main(String [] args){
   System.out.println("Replace me with your code!");
   new SubString("Hello").subString(1,7);
 }
 private void subString(int startIndex, int endIndex){
     System.out.println("Hello");
     if(givenString==null || startIndex<0 || endIndex>givenString.length()){
         throw new IndexOutOfBoundsException("Start Index= "+ startIndex+ " end Index = "+endIndex);
     }
     char[] subString = new char[endIndex-startIndex];
     int index = 0;
     for(int i=0; i<givenString.toCharArray().length; i++){
         if(i>=startIndex && i<endIndex){
             subString[index++] = givenString.charAt(i);
         }
     }
     String str1 = new String(subString);
     System.out.println(subString);
     System.out.println(str1);
     System.out.println(givenString.substring(2,4));
 }
}