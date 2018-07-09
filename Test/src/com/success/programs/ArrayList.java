package com.success.programs;

import java.util.Arrays;

public class ArrayList {
	
	private Object[] values;
	private int actualSize = 0;
	
	public ArrayList(){
		values = new Object[10];//inital size
	}
	public void add(Object obj){
		if(values.length==actualSize ){
			//increae size
			values = Arrays.copyOf(values, actualSize+(actualSize/2)+1);
		}
		values[actualSize] = obj;
		actualSize++;
	}
	public Object get(int index){
		if(index<0 || index>actualSize){
			throw new ArrayIndexOutOfBoundsException("out of range!!");
		}
		return values[index];
	}
	
	public int getSize(){
		return actualSize;
	}
	public static void main(String[] args) {
		ArrayList list = new ArrayList();
		list.add("A");
		list.add("A");
		list.add("A");
		list.add("A");
		list.add("A");
		list.add("A");
		list.add("A");
		list.add("A");
		list.add("A");
		list.add("A");
		list.add("A");
		list.add("A");
		list.add("A");
		list.add("A");
		list.add("A");
		list.add("A");
		list.add("A");
		list.add("A");
		System.out.println(list.getSize());
	}
}
