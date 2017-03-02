package com.success.amazon;

public class PrimeNumber {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println(Math.sqrt(81));
		System.out.print("2"); 
		boolean status = true;
		for(int i=3; i<100; i++){
			for(int j=2;j<=Math.sqrt(i); j++){
				if(i%j==0){
					status = false;
					break;
				}
			}
			if(status){
				System.out.print(","+i);
			}
			status = true;
		}
	}

}
