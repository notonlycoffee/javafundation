package com.audaque.wwq.wwq.core.concurrent_book.chapter1.one;

/**
 * 
 *Title:
 *Description:直接继承Thread类
 *@author q
 *2016年5月4日上午10:42:57
 */
public class Calculator_Thread_2 extends Thread {
	
	private int num;
	
	public Calculator_Thread_2(int num) {
		this.num = num;
	}
	
	public void run() {
		for( int i = 0 ; i < 10 ; i ++) {
			System.out.printf("%s: %d * %d = %d\n",Thread.currentThread().getName(),num,i,num*i);
			if(i == 5) {
				System.exit(0);  //如果初始线程（执行main()方法的主线程）运行结束，
				                 //其他的线程还是会继续执行直到执行完成。但是如果某个线
								 //程调用System.exit()指示终结程序，那么全部的线程都会结束执行。
			}
		}
	}
}
