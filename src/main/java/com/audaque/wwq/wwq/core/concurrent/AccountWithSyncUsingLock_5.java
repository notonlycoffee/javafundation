package com.audaque.wwq.wwq.core.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * 显式添加锁实现多线程安全问题
 * 添加锁之后使用finally进行释放锁
 * @author q
 *
 */
public class AccountWithSyncUsingLock_5 {
	private static Account account = new Account();
	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();
		for( int i = 0 ; i < 100 ; i++) {
			executor.execute(new AddAPennyTask());
		}
		executor.shutdown();
		
		while(!executor.isTerminated()) {
			System.out.println(account.getBalance());
		}
	}
	
	
	
	private static class AddAPennyTask implements Runnable {
		public void run() {
			account.deposit(1);
		}
	}
	
	private static class Account {
		private int balance;
		private static Lock lock = new ReentrantLock(); //显式添加锁，对方法进行锁定，实现线程安全
		public int getBalance() {
			return this.balance;
		}
		
		public void deposit(int amount) {
			lock.lock();
			try {
				int temp = this.balance + amount;
				Thread.sleep(5);
				this.balance = temp;
			}catch(Exception e) {
				throw new RuntimeException(e);
			} finally {
				lock.unlock();
			}
		}
	}
}
