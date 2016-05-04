package com.audaque.wwq.wwq.core.concurrent_book.chapter1.one;

/**
 * 
 *Title:任务类实现Runnable类
 *Description:调用实现Runnable接口的 run()方法也不会创建一个新的执行线程。只有调用start()方法才能创建一个新的执行线程.
 *@author q
 *2016年5月4日上午10:42:35
 */

public class Calculator_1 implements Runnable {
	private int number;
	public Calculator_1(int number) {
		this.number = number;
	}
	
	public void run() {
		for(int i = 0 ; i < 10; i++) {
			System.out.printf("%s: %d * %d = %d\n",Thread.currentThread().getName(),number,i,i*number);
		}
	}
}
