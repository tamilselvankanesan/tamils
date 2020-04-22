package com.success.java8;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class TestCompletableFuture {

	public static void main(String[] args) throws InterruptedException {
		/*
		 * CompletableFuture<Supplier<String>> cf = new
		 * CompletableFuture<Supplier<String>>(); cf.thenAccept(data -> data.get());
		 * System.out.println("Built..."); cf.complete(() -> sayHello());
		 * System.out.println("Done");
		 */
		CompletableFuture.supplyAsync(()->sayHello());
		System.out.println("Done 1");
		System.out.println("dfdsf "+Thread.currentThread().getName());
//		cf1.thenAccept(data-> System.out.println(data));
	}
	
	public static String sayHello() {
		try {
			System.out.println("1121 "+Thread.currentThread().getName());
			Thread.sleep(1000);
			System.out.println("Saying hello");
			System.out.println(Thread.currentThread().getName());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Hello!!!";
	}
}
