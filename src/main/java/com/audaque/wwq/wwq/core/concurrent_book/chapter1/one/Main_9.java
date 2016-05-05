package com.audaque.wwq.wwq.core.concurrent_book.chapter1.one;

import java.util.concurrent.TimeUnit;

public class Main_9 {
	public static void main(String[] args) {
		Task_9 task = new Task_9();
		Thread t = new Thread(task);
		t.setUncaughtExceptionHandler(new ExceptionHandler_9());
		t.start();
	}
}