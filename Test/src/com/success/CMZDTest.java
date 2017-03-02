package com.success;

import java.util.ArrayList;
import java.util.List;

public class CMZDTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> myGrpList = new ArrayList<>();
		myGrpList.add(50000);myGrpList.add(1);myGrpList.add(50005);
		myGrpList.add(53051);myGrpList.add(53354);
		
		List<Integer> permissionsGrpList = new ArrayList<>();
		permissionsGrpList.add(53092);permissionsGrpList.add(53051);permissionsGrpList.add(52932);
		int count =0;
		for(Integer permsissionsGrp : permissionsGrpList){
			if(myGrpList.contains(permsissionsGrp)){
				count ++;
			}
		}
		System.out.println("Count -->"+count);
	}

}
