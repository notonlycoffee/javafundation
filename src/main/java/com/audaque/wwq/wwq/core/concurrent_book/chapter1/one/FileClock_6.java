package com.audaque.wwq.wwq.core.concurrent_book.chapter1.one;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class FileClock_6 implements Runnable {

	public void run() {
		try {  //这里要把try-catch放在for循环外面,如果放在里面,就算中断了也会继续执行循环
			for (int i = 0; i < 10; i++) {
				System.out.printf("%s\n", new Date());
				TimeUnit.SECONDS.sleep(1);
			}
		} catch (InterruptedException e) {
			System.out.println("The FileClock has been interrupted");
		}
		
		
		/** 这种方式就算线程已经中断,但是还会继续执行循环内容,不建议这样写
		for(int i = 0 ; i < 10 ; i++) {
			System.out.printf("%s\n", new Date());
			try {
				TimeUnit.SECONDS.sleep(1);
			}catch (InterruptedException e) {
				System.out.println("The FileClock has been interrupted");
			}
		}
		**/

	}
}
