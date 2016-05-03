package com.audaque.wwq.wwq.core.concurrent;


/**
 * 使用runnable和Thread运行线程，必须要在main方法中运行，
 * 不能再junti的@Test里面运行，不然会提前终止@Test测试而没有运行完全runnable定义的任务
 * sleep()和join()方法都会抛出InterruptedException异常
 * @author jc
 *
 */
public class TaskThreadDemo_1 {
	public static void main(String[] args) {
		PrintChar pc = new PrintChar("aa", 100);
		PrintNum pn = new PrintNum(100);
		Thread t1 = new Thread(pc);
		Thread t2 = new Thread(pn);
		t1.start();
		t2.start();
		
	}
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
					Thread.sleep(1);   //使线程休眠多少毫秒，这里是休眠1毫秒
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
	

