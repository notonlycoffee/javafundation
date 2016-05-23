package com.audaque.wwq.wwq.core.another_concurrentbook.chapter5;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;


/**
 * 
 *Title:
 *Description:
 *	无锁算法实现线程的安全操作,使用的是CAS(Compare and Swap)无锁算法
 *@author q
 *2016年5月23日上午11:16:37
 */
public class AtomicAccountTest extends Thread{
	private AtomicAccount a;
	private int delay;
	public AtomicAccountTest(AtomicAccount a , int delay) {
		this.a = a;
		this.delay = delay;
	}
	
	public static void main(String[] args) {
		AtomicAccount a = new AtomicAccount(100);
		new AtomicAccountTest(a, 1).start();
		new AtomicAccountTest(a, 0).start();
	}
	
	public void run() {
		a.withdraw(100, delay);
	}
}

class AtomicAccount {
	private AtomicLong balance;
	public AtomicAccount(long balance) {
		this.balance = new AtomicLong(balance);
		System.out.println("total money is " + this.balance);
	}
	
	public void withdraw(long money,int delay) {
		long oldValue = balance.get();
		if(oldValue >= money) {
			try {
				TimeUnit.SECONDS.sleep(delay);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			
			if(balance.compareAndSet(oldValue, oldValue-money)) {
				System.out.println(Thread.currentThread().getName()+" withdraw money " + money+" success");
			} else {
				System.out.println(Thread.currentThread().getName()+" withdraw failed");
			}
		} else {
			System.out.println("failed");
		}
	}
	public long get() {
		return balance.get();
	}
}
