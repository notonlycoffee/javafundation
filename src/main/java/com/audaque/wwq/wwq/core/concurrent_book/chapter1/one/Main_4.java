package com.audaque.wwq.wwq.core.concurrent_book.chapter1.one;

public class Main_4 {
	public static void main(String[] args) {
		Thread task = new PrimeGenerator_4();
		task.start();
		try {
			Thread.sleep(1000);
		} catch(Exception e) {
			e.printStackTrace();
		}
		task.interrupt();
	}
}
