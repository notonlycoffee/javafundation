package com.audaque.wwq.wwq.core.another_concurrentbook.chapter3;

public class AtomicCounterTest_2 extends Thread {
	
	AtomicCounter_2 counter;
	
	public AtomicCounterTest_2(AtomicCounter_2 counter) {
		this.counter = counter;
	}
	
	public void run() {
		int i = counter.increment();
		System.out.println("generated number:" + i);
	}
	
	
	public static void main(String[] args) {
		AtomicCounter_2 counter = new AtomicCounter_2();
		for(int i=0 ; i < 10 ; i++) {
			new AtomicCounterTest_2(counter).start();
		}
	}
	
}
