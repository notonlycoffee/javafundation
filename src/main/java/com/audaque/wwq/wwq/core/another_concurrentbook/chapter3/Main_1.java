package com.audaque.wwq.wwq.core.another_concurrentbook.chapter3;

import java.util.concurrent.TimeUnit;

public class Main_1 implements Runnable {
	private SynchronizedCounter_1 s;
	public Main_1(SynchronizedCounter_1 s) {
		this.s = s;
	}
	public static void main(String[] args) {
		SynchronizedCounter_1 ss = new SynchronizedCounter_1();
		Thread t1 = new Thread(new Main_1(ss));
		Thread t2 = new Thread(new Main_1(ss));
		Thread t3 = new Thread(new Main_1(ss));
		t1.start();
		t2.start();
		t3.start();
		try {
			t1.join();
			t2.join();
			t3.join();
		} catch(InterruptedException e) {
			
		}
		System.out.println(ss.getNumber());
	}
	
	public void run() {
		try {
		for(int  i = 0 ; i < 10 ; i++) {
			s.increment();
			System.out.println(Thread.currentThread().getName()+" : " + s.getNumber());
			TimeUnit.SECONDS.sleep(1);
		}
		} catch(Exception e) {
			
		}
	}
}
