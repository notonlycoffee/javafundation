package com.audaque.wwq.wwq.core.another_concurrentbook.chapter2;

import java.util.concurrent.TimeUnit;

public class ThreadLocalExample_3 {
	public static class MyRunnable implements Runnable {
		private ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();
		
		
		public void run() {
			threadLocal.set((int)(Math.random()*100D));
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(threadLocal.get());
		}
		
		public static void main(String[] args) {
			MyRunnable my = new MyRunnable();
			Thread t1 = new Thread(my);
			Thread t2 = new Thread(my);
			t1.start();
			t2.start();
		}
	}
}
