package com.audaque.wwq.wwq.core.concurrent_book.chapter1.one;

import java.util.concurrent.TimeUnit;

public class Task_9 implements Runnable {

	@Override
	public void run() {
		int number = 34 / 0;
	}

}
