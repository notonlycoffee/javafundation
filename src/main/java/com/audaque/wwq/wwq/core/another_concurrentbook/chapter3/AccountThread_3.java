package com.audaque.wwq.wwq.core.another_concurrentbook.chapter3;

public class AccountThread_3 extends Thread {
	AtomicAccount_3 account;
	int delay;
	
	public AccountThread_3(AtomicAccount_3 account , int delay) {
		this.account = account;
		this.delay = delay;
	}
	
	public void run() {
		account.withdraw(100, delay);
	}
	
	public static void main(String[] args) {
		AtomicAccount_3 account = new AtomicAccount_3(100);
		AccountThread_3 t1 = new AccountThread_3(account,1);
		AccountThread_3 t2 = new AccountThread_3(account, 0);
		t1.start();
		t2.start();
	}
}
