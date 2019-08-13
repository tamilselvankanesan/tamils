package com.success.java8;

import java.util.function.Supplier;

public class TestSupplier {

	public static void main(String[] args) {
		
		//functional interface with just one method T Get -> which returns the type T 
		Supplier<String> supplier = () -> "hello";
		
		String some = supplier.get();
	}
}
