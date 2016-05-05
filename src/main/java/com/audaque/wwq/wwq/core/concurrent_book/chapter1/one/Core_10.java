package com.audaque.wwq.wwq.core.concurrent_book.chapter1.one;

import java.util.concurrent.TimeUnit;

public class Core_10 {
	public static void main(String[] args) {
		UnsafeTask_10 task = new UnsafeTask_10();
		try {
		for( int i = 0 ; i < 10 ; i++) {
			Thread thread = new Thread(task);
			thread.setName("thread"+i);
			thread.start();
			TimeUnit.SECONDS.sleep(2);
		}
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
}
