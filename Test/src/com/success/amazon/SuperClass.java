package com.success.amazon;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class SuperClass {
	public void get() throws ParseException{
		try {
			new SimpleDateFormat().parse("sdsa");
		} catch (ParseException e) {
			throw e;
		}
	}
	private void add(){
		Integer a = new Integer(10);
		addA(a);
		System.out.println(a);
	}
	private void addA(Integer b){
		b++;
		System.out.println(b);
	}
	
	public static void main(String[] args) {
		new SuperClass().add();
	}
}
