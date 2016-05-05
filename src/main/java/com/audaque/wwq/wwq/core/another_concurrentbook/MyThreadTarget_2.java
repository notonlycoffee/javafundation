package com.audaque.wwq.wwq.core.another_concurrentbook;

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
