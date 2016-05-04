package com.audaque.wwq.wwq.core.concurrent_book.chapter1.one;

public class Calculator_1 implements Runnable {
	private int number;
	public Calculator_1(int number) {
		this.number = number;
	}
	
	public void run() {
		for(int i = 0 ; i < 10; i++) {
			System.out.printf("%s: %d * %d = %d\n",Thread.currentThread().getName(),number,i,i*number);
		}
	}
			
	
}
