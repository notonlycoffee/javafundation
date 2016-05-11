package com.audaque.wwq.wwq.core.another_concurrentbook.chapter2;


/**
 * 
 *Title:
 *Description:
 *	1.在多线程中,一个线程存钱,一个线程取钱,会出现线程不安全的问题,最好使用同步语句进行同步操作
 *@author q
 *2016年5月11日下午2:55:08
 */
public class BankAccount_2 {
	private int number;
	private int balance;
	
	public BankAccount_2() {}
	
	public BankAccount_2(int number,int balance) {
		this.number = number;
		this.balance = balance;
	}
	
	public int getBalance() {
		return this.balance;
	}
	
	public void deposit(int amount) {
			this.balance += amount;
	}
	
	public void withdraw(int amount) {
		this.balance -= amount;
	}
	
	public static void main(String[] args) {
		BankAccount_2 b = new BankAccount_2(1, 1000);
		Thread t1 = new Thread(new Depositor(b,100),"depositor");
		Thread t2 = new Thread(new Withdrawer(b, 100),"withdrawer");
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(b.getBalance());
	}
	
	static class Depositor implements Runnable {
		BankAccount_2 bankAccount;
		int amount;
		public Depositor(BankAccount_2 bankAccount,int amount) {
			this.bankAccount = bankAccount;
			this.amount = amount;
		}
		public void run() {
			for( int  i = 0 ; i < 100000 ; i++) {
				bankAccount.deposit(amount);
			}
		}
		
	}
	
	static class Withdrawer implements Runnable {
		BankAccount_2 bankAccount;
		int amount;
		public Withdrawer(BankAccount_2 bankAccount,int amount) {
			this.bankAccount = bankAccount;
			this.amount = amount;
		}
		public void run() {
			for(int i = 0 ; i < 100000; i++) {
				bankAccount.withdraw(amount);
			}
		}
	}
}
