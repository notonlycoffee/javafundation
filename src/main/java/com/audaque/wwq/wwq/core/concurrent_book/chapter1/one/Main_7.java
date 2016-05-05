package com.audaque.wwq.wwq.core.concurrent_book.chapter1.one;

import java.util.Date;

/**
 * 
 *Title:
 *Description:
 *	1.两个线程的启动顺序主要取决于Thread这个类的new的顺序,如果new Thread(ncloader)放在new Thread(dsloader)前面,则先启动ncloader这个线程
 *@author q
 *2016年5月5日上午11:03:31
 */
public class Main_7 {
	public static void main(String[] args) {
		
		DataSourceLoader_7 dsLoader = new DataSourceLoader_7();
		Thread thread1 = new Thread(dsLoader,"DataSourceThread");//具有顺序区分
		
		
		NetWorkConnectionsLoader_7 ncLoader = new NetWorkConnectionsLoader_7();
		Thread thread2 =  new Thread(ncLoader,"NetworkConnectionsLoader");//具有顺序区分
		thread1.start();
		thread2.start();
		
		
		try {
			thread2.join();
			thread1.join();
			//thread2.join(1000);//调用这个方法,main线程暂停,知道以下其中一个条件发生:thread2结束运行或者1000毫秒过去了
			
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.printf("Main: Configuration has been loaded: %s\n",new Date());
		
	}
}
