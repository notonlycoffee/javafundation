package com.audaque.wwq.wwq.core.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 调用一个对象的同步实例方法要求给该对象加锁
 * 调用一个类的同步静态方法要求对该类加锁
 * @author jc
 *
 */
public class AccountWithoutSync_4 {
	private static Account account = new Account();
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		for(int i = 0 ; i < 100 ; i++) {
			executorService.execute(new AddAccount());
		}
		
		executorService.shutdown();
		
		while(!executorService.isTerminated()) {
			System.out.println(account.getBalance());
		}
	}
	
	private static class AddAccount implements Runnable {
		public void run() {
				account.deposit(1);
			/*第二种实现线程安全的方式，在任务中调用这个方法的时候使用sysnchronized
			 * synchronized (account) {
				account.deposit(1);
			}*/
		}
		
	}
	
	private static class Account {
		private int balance = 0;
		public int getBalance() {
			return this.balance;
		}
		/*第一种线程安全的方式，在方法签名中加入sysnchronized
		 * public synchronized void deposit(int b) {
			int re = this.balance + b;
			try {
				Thread.sleep(2);
			} catch(Exception e) {
				throw new RuntimeException(e);
			}
			this.balance = re;
		}*/
		public void deposit(int b) {
			int re = this.balance + b;
			try {
				Thread.sleep(2);
			} catch(Exception e) {
				throw new RuntimeException(e);
			}
			this.balance = re;
			/*第三种实现多线程安全的方式，在方法体中加入sysnchronized
			 * synchronized (this) {
				int re = this.balance + b;
				try {
					Thread.sleep(2);
				} catch(Exception e) {
					throw new RuntimeException(e);
				}
				this.balance = re;
			}*/
		}
	}
}


