package com.success.java8;

import java.util.function.Supplier;

public class Delegate {

	private static int doubleIt(int num) {
		System.out.println("doubleing it ");
		return num*2;
	}
	
	public static void main(String[] args) {
		int num = 13;
		Lazy<Integer> tt = new Lazy<>(() -> doubleIt(num));
				
		if(num>12 && tt.get() > 20) {
			System.out.println("doubled "+num +" is "+ tt.get());
		}else {
			System.out.println("false");
		}
	}
	
}
class Lazy<T> {
	private T instance;
	private Supplier<T> supplier;
	public Lazy() {
		
	}
	public Lazy(Supplier<T> theSupplier) {
		this.supplier = theSupplier;
	}
	public T get() {
		if(instance==null) {
			instance = this.supplier.get();
		}
		return instance;
	}
}