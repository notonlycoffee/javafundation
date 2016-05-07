package com.audaque.wwq.wwq.core.another_concurrentbook;

/**
 * 
 *Title:
 *Description: 
 *	1.sleep()方法声明可以抛出InterruptedException 异常，当另一个线程中断了
 *		已经启动sleep的当前线程时会抛出这个异常。下面的程序只有主线程，所以
 *		不用担心有其他线程中断睡眠，所以不用担心抛出这个异常
 * @author jc
 * 2016年5月7日上午10:57:49
 */
public class SleepTest {
	public static void main(String[] arg) {
		
		String [] args = {"one","two","three","four"};
		long start = System.nanoTime();
		for(int i = 0 ; i < args.length ; i++) {
			try {
				System.out.println(args[i]);
				Thread.sleep(1000);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		long end = System.nanoTime();
		
		System.out.println("总的时间为：" + (end-start)/1000000);
		
	}
}
