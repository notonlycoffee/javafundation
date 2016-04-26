package com.audaque.wwq.wwq.core.concurrent;

import org.junit.Test;

public class ErrorDemo_2 {
	
	
	//使用junit测试多线程会因为运行这个程序的线程提前结束而无法运行里面的任务
	@Test
	public void demo() {
		PrintChar pc = new PrintChar("aa", 1000);
		PrintNum pn = new PrintNum(1000);
		Thread t1 = new Thread(pc);
		Thread t2 = new Thread(pn);
		t1.start();
		t2.start();
	}
	
	class PrintChar implements Runnable {
		private String str;
		private int num;
		public PrintChar(){}
		public PrintChar(String str,int num) {
			this.str = str;
			this.num=num;
		}
		public void run() {
			for(int i = 0 ; i < this.num ; i++) {
				System.out.print(str+"  ");
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	
	class PrintNum implements Runnable {
		private int num;
		public PrintNum(){}
		public PrintNum(int num) {
			this.num = num;
		}
		public void run() {
			for( int i = 0 ; i < this.num ; i++) {
				System.out.print(num-i+"  ");
				Thread.yield();
			}
		}
		
	}
	
	
	
	
	
}
