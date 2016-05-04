package com.audaque.wwq.wwq.core.concurrent_book.chapter1.one;

import java.util.concurrent.TimeUnit;

public class FileMain_6 {
	public static void main(String[] args) {
		Thread t = new Thread(new FileClock_6());
		t.start();
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		t.interrupt();
	}
}
