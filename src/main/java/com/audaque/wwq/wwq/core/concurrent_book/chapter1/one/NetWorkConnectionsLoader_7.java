package com.audaque.wwq.wwq.core.concurrent_book.chapter1.one;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class NetWorkConnectionsLoader_7 implements Runnable {
	public void run() {
		System.out.printf("Beginning network connections loading : %s\n",new Date());
		try {
			TimeUnit.SECONDS.sleep(6);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.printf("Newwork connections loading has finished : %s\n",new Date());
	}
}
