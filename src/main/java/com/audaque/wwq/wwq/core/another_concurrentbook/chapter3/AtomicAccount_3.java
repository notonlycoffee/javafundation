package com.audaque.wwq.wwq.core.another_concurrentbook.chapter3;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class AtomicAccount_3 {
	private AtomicLong balance;
	public AtomicAccount_3(long money) {
		balance = new AtomicLong(money);
	}
	
	public AtomicAccount_3() {}
	
	public void withdraw(long money,int delay) {
		long oldvalue = balance.get();
		if(oldvalue >= money) {
			try {
				TimeUnit.SECONDS.sleep(delay);
				if(balance.compareAndSet(oldvalue, oldvalue-money)) {
					System.out.println(Thread.currentThread().getName()+" withdraw "+ money + "successful"+balance);
				} else {
					System.out.println(Thread.currentThread().getName() + " withdraw failed" + balance);
				}
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println(Thread.currentThread().getName()+" balance is not enough ,withdraw failed!"+balance);
		}
	}
	
	public void deposit(long money) {
		balance.addAndGet(money);
	}
}
