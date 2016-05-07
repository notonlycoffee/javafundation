package com.audaque.wwq.wwq.core.another_concurrentbook.chapter1;


/**
 * 
 *Title:
 *Description: 
 *	1.创建线程目标对象，并且传给Thread的构造方法创建线程
 *	2.实现Runnable接口的类创建的对象称作线程的目标对象
 * @author jc
 * 2016年5月7日上午10:06:14
 */
public class MyThreadTarget_2 implements Runnable {
	
	public void run() {
		for(int  i = 0 ; i < 20 ; i++) {
			System.out.println(Thread.currentThread().getName()+":"+i);
		}
	}
	
	public static void main(String[] args) {
		for(int  i = 0 ; i < 5 ; i++) {
			new Thread(new MyThreadTarget_2(),"thread"+i).start();;
		}
	}

}
