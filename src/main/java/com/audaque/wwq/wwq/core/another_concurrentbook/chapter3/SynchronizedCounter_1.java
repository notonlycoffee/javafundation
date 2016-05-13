package com.audaque.wwq.wwq.core.another_concurrentbook.chapter3;

public class SynchronizedCounter_1{
	private int number=0;
	
	public synchronized int getNumber() {
		return this.number;
	}
	
	public synchronized void increment() {
		++this.number;
	}
	
	public synchronized void decrement() {
		--this.number;
	}
	
}
