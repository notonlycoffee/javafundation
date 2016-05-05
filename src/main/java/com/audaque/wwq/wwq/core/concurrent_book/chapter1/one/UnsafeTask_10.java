package com.audaque.wwq.wwq.core.concurrent_book.chapter1.one;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class UnsafeTask_10 implements Runnable{
	private Date startDate;
	
	public void run() {
		startDate = new Date();
		System.out.printf("Starting Thread : %s : %s \n",Thread.currentThread().getName(),startDate);
		try {
			TimeUnit.SECONDS.sleep((int)Math.rint(Math.random()*10));
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("Thread Finished: %s : %s \n", Thread.currentThread().getName(),startDate);
	}
}
