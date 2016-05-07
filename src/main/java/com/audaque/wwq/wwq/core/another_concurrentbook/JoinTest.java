package com.audaque.wwq.wwq.core.another_concurrentbook;

import java.util.concurrent.TimeUnit;

/**
 * 
 *Title:
 *Description: 
 *	1.join会抛出InterruptedException异常
 * @author jc
 * 2016年5月7日上午11:24:26
 */
public class JoinTest extends Thread {
	static int result = 0;
	public JoinTest(String name) {
		super(name);
	}
	
	public static void main(String[] args) {
		System.out.println("主线程开始执行");
		Thread t = new JoinTest("计算线程");
		t.start();
		System.out.println("result:"+result);
		try {
			long start = System.nanoTime();
			t.join();
			//t.join(2000);  //使用这一句会发现主线程没有等待计算线程执行完毕就直接输出结果了，结果仍然为0，join等待2000毫秒之后如果继续执行主线程
			long end = System.nanoTime();
			System.out.println((end-start)/1000000+"毫秒后result:"+result);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		System.out.println(this.getName()+"开始计算.....");
		try {
			TimeUnit.SECONDS.sleep(4);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		result = (int) (Math.random()*10000);
		System.out.println(this.getName()+"结束计算");
	}
	
}
