package com.audaque.wwq.wwq.core.another_concurrentbook;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 *Title:
 *Description:
 *	1.线程中只生成了两个线程对象,100个线程目标对象共享这两个线程对象
 *@author q
 *2016年5月5日下午5:44:04
 */
public class TestThreadPool_3 {
	public static void main(String[] args) {
		ExecutorService exec = Executors.newFixedThreadPool(2);//只生成两个线程对象
		for(int index = 0 ; index<100;index++) {//创建100个线程目标执行对象
			Runner run = new Runner(index);
			exec.execute(run);
		}
		
		exec.shutdown();
	}
}

class Runner implements Runnable {
	int index = 0;
	public Runner(int index) {
		this.index = index;
	}
	public void run() {
		long time = (long)(Math.random()*1000);
		System.out.println("线程:"+Thread.currentThread().getName()+"(目标对象"+index+")"+":Sleeping"+time+"ms");
	}
}
