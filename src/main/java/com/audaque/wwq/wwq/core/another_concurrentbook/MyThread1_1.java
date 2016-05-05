package com.audaque.wwq.wwq.core.another_concurrentbook;

public class MyThread1_1 extends Thread{
	
	public MyThread1_1(String name) {
		super(name);
	}
	public void run() {
		for(int i = 0 ; i < 20; i++) {
			System.out.println(this.getName()+":"+i);
		}
	}
	
	public static void main(String[] args) {
		for(int i = 0 ; i< 5 ; i++) {
			new MyThread1_1("thread"+i).start();
		}
	}

}
