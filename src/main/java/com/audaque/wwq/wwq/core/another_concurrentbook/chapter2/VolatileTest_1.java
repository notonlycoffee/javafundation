package com.audaque.wwq.wwq.core.another_concurrentbook.chapter2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class VolatileTest_1 {
	
//	private volatile static int balance=10;
	
	private static int balance=10;
	
	public VolatileTest_1() {
		
	}
	
	public static void main(String[] args) {
		try {
			System.setOut(new PrintStream(new FileOutputStream(new File("D:"+"one.txt"))));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Thread t2 = new Thread(new UpdateNumber());
		Thread t4 = new Thread(new ShowNumber());
		Thread t3 = new Thread(new UpdateNumber());
		Thread t1 = new Thread(new UpdateNumber());
		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}
	
	static class UpdateNumber implements Runnable {
		public void run() {
			balance = (int)(Math.random()*1000);
			System.out.println(Thread.currentThread().getName()+"  balance is : " + balance);
		}
	}
	
	static class ShowNumber implements Runnable {
		public void run() {
			balance = 9000000;
			for(int  i = 0 ; i < 1000; i++) {
				System.out.println(balance);
			}
		}
	}
	
}
