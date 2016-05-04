package com.audaque.wwq.wwq.core.concurrent_book.chapter1.one;

public class Caculator_Thread_2 extends Thread {
	
	private int num;
	
	public Caculator_Thread_2(int num) {
		this.num = num;
	}
	
	public void run() {
		for( int i = 0 ; i < 10 ; i ++) {
			System.out.printf("%s: %d * %d = %d\n",Thread.currentThread().getName(),num,i,num*i);
		}
	}
}
