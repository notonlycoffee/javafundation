package com.audaque.wwq.wwq.core.concurrent_book.chapter1.one;

import java.util.concurrent.TimeUnit;

public class Main_5 {
	public static void main(String[] args) {
		FileSearch_5 search = new FileSearch_5("D:\\","GbSpy.dll");
		Thread thread = new Thread(search);
		thread.start();
		
		try {
			 TimeUnit.SECONDS.sleep(10);//睡眠10秒钟,建议使用这种睡眠时间,而不用Thread.sleep(),因为Thread.sleep的可读性比较差
//			 TimeUnit.MINUTES.sleep(10); //睡眠10分钟
//			 TimeUnit.HOURS.sleep(10);   //睡眠10小时
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		thread.interrupt();
	}
}
