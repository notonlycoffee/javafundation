package com.audaque.wwq.wwq.core.another_concurrentbook.chapter3;

import java.util.concurrent.TimeUnit;

public class Account_3 {
	private double balance;
	public Account_3(double money) {
		this.balance = money;
	}
	
	public Account_3() {}
	
	public void withdraw(double money,int delay) {
		if(this.balance >= money) {
			try {
				TimeUnit.SECONDS.sleep(delay);
				this.balance = this.balance-money;
				System.out.println(Thread.currentThread().getName()+" withdraw "+ money + "successful"+balance);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println(Thread.currentThread().getName()+" balance is not enough ,withdraw failed!"+balance);
		}
	}
	
	public void deposit(double money) {
		this.balance = this.balance - money;
	}
}
