package com.audaque.wwq.wwq.core.another_concurrentbook;

/**
 * 
 *Title:
 *Description: 
 *	1.如果一个线程长时间没有调用能够抛出InterruptedException异常的
 *		方法，那么线程就必须定期的调用Thread.interrupted方法检测
 *		是否已经被中断
 *	2.如果一个线程判断到自己被中断，可以抛出InterruptedException异常，然后再catch里面处理后续的内容
 * @author jc
 * 2016年5月7日下午12:19:25
 */
public class InterruptTest_7 extends Thread {
	static int result;
	public InterruptTest_7(String name) {
		super(name);
	}
	
	public static void main(String[] args) {
		System.out.println("主线程开始执行");
		Thread t = new InterruptTest_7("计算线程");
		t.start();
		System.out.println("result : " + result);
		try {
			long start = System.nanoTime();
			t.join(10);
			long end = System.nanoTime();
			t.interrupt();
			System.out.println((end-start)/1000000+"毫秒之后result:"+result);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		System.out.println(this.getName()+"开始执行");
		try {
		for(int i = 0 ; i < 10000000 ; i++) {
			result ++;
			if(Thread.interrupted()) {//因为没有调用抛出中断异常的方法，这里需要定期检测线程是否被中断 
				System.out.println("线程被中断");
				throw new InterruptedException();//抛出中断异常，在catch中处理中断后的内容
			}
		}
		} catch(InterruptedException e) {
			return ;
		}
		System.out.println(this.getName()+"计算结束");
	}
}
