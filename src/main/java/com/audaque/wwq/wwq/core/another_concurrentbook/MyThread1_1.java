package com.audaque.wwq.wwq.core.another_concurrentbook;

/**
 * 
 *Title:
 *Description: 
 *	1.这种方式吧线程执行的逻辑代码直接写在了Thread的子类中，这样根据线程的概念模型，虚拟cpu和代码
 *		混合在一起。并且java是单继承机制，线程体继承Thread类后，就不能再继承其他类了，线程的扩展受影响
 * @author jc
 * 2016年5月7日上午10:04:01
 */
public class MyThread1_1 extends Thread{
	
	public MyThread1_1(String name) {
		super(name);
	}
	public void run() {
		for(int i = 0 ; i < 20; i++) {
			System.out.println(Thread.currentThread().getName()+"  运行了  :"+i);
		}
	}
	
	public static void main(String[] args) {
		for(int i = 0 ; i< 5 ; i++) {
			new MyThread1_1("thread "+i).start();
		}
	}

}
