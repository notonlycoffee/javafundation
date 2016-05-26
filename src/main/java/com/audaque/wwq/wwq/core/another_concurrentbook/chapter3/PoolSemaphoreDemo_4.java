package com.audaque.wwq.wwq.core.another_concurrentbook.chapter3;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class PoolSemaphoreDemo_4 {
	private final static int MAX_AVAILABLE = 5;
	private Semaphore se = new Semaphore(MAX_AVAILABLE, true);
	public static void main(String[] args) {
		final PoolSemaphoreDemo_4 demo = new PoolSemaphoreDemo_4();
		Runnable runner = new Runnable() {
			public void run() {
				try {
					Object o;
					o = demo.getValue();
					System.out.println(Thread.currentThread().getName()+" acquire "+o);
					TimeUnit.SECONDS.sleep(2);
					demo.returnValue(o);
					System.out.println(Thread.currentThread().getName()+" release "+o);
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		
		for(int i = 0 ; i < 10 ; i++) {
			new Thread(runner,"thread "+i).start();
		}
	}
	
	public Object getValue() {
		try {
		se.acquire();
		} catch(Exception e) {
			
		}
		return getValueMethod();
	}
	
	public void returnValue(Object o) {
		if(returnValueMethod(o)) {
			se.release();
		}
	}
	
	protected Object items [] = {"AAA","BBB","CCC","DDD","EEE"};
	protected boolean use[] = new boolean[MAX_AVAILABLE];
	
	public synchronized Object getValueMethod() {
		for(int i = 0 ; i < MAX_AVAILABLE ; i++) {
			if(!use[i]) {
				use[i] = true;
				return items[i];
			}
		}
		return null;
	}
	
	public synchronized boolean returnValueMethod(Object o) {
		for(int i = 0 ; i < MAX_AVAILABLE ; i++) {
			if(o == items[i]) {
				if(use[i]) {
					use[i] = false;
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}
}
