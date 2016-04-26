package com.audaque.wwq.wwq.core.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AccountWithoutSync_4 {
	private static Account account = new Account();
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		for(int i = 0 ; i < 100 ; i++) {
			executorService.execute(new AddAccount());
		}
		
		executorService.shutdown();
		
		while(executorService.isTerminated()) {
			System.out.println(account.getBalance());
		}
	}
	
	private static class AddAccount implements Runnable {

		public void run() {
			account.deposit(1);
		}
		
	}
	
	private static class Account {
		private int balance = 0;
		public int getBalance() {
			return this.balance;
		}
		public void deposit(int b) {
			int re = this.balance + b;
			try {
				Thread.sleep(2);
			} catch(Exception e) {
				throw new RuntimeException(e);
			}
			this.balance = re;
		}
	}
}


