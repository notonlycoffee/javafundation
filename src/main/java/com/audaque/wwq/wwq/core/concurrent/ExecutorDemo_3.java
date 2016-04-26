package com.audaque.wwq.wwq.core.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 使用线程池，运行多个任务的时候可以使用
 * @author jc
 *
 *使用Executors静态类创建一个executorService,作为线程池管理添加的线程，
 *executorService提供了shutdown(),shutdownnow(),isshutdown(),isTerminated()方法
 *管理线程池的线程
 *
 *
 *newFixedThreadPool()方法创建的线程池里面的线程可以用来执行不同的线程
 */
public class ExecutorDemo_3 {
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(3);//这个方法中，如果线程完成了任务的执行，
						//它可以被重新使用执行另外的任务，如果线程池中所有的线程都不是处于空闲状态
						//，而是有任务在等待执行，那么在关机之前，如果由于一个错误终止了一个线程，就会创建一个新线程来替代它
		executorService.execute(new PrintChar2("aaa", 100));
		executorService.execute(new PrintChar2("bbb", 100));
		executorService.execute(new PrintNum(100));
		
		executorService.shutdown();//线程池里的线程不能接受新的任务，但是会直到现有的任务运行完毕之后才终止
	}
}


class PrintChar2 implements Runnable {
	private String str;
	private int num;
	public PrintChar2(){}
	public PrintChar2(String str,int num) {
		this.str = str;
		this.num=num;
	}
	public void run() {
		for(int i = 0 ; i < this.num ; i++) {
			System.out.print(str+"  ");
			try {
				Thread.sleep(1);   //使线程休眠多少毫秒，这里是休眠1毫秒
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}


class PrintNum2 implements Runnable {
	private int num;
	public PrintNum2(){}
	public PrintNum2(int num) {
		this.num = num;
	}
	public void run() {
		Thread t3 = new Thread(new PrintChar("gg", 100));
		t3.start();
		for( int i = 0 ; i < this.num ; i++) {
			System.out.print(num-i+"  ");
			if(i==50) {
				try {
					t3.join();//当i等于50的时候，这里需要等待t3这个线程完成之后才能继续printNum这个任务
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}