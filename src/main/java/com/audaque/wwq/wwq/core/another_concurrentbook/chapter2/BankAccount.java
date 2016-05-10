package com.audaque.wwq.wwq.core.another_concurrentbook.chapter2;

public class BankAccount {
	private int number;
	private int balance;
	
	public BankAccount() {}
	
	public BankAccount(int number,int balance) {
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
		BankAccount b = new BankAccount(1, 1000);
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
		BankAccount bankAccount;
		int amount;
		public Depositor(BankAccount bankAccount,int amount) {
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
		BankAccount bankAccount;
		int amount;
		public Withdrawer(BankAccount bankAccount,int amount) {
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
