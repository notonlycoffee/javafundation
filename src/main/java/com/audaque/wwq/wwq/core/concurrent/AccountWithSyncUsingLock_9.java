package com.audaque.wwq.wwq.core.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 *Title:使用信号量实现临界资源的访问
 *Description: 
 * @author jc
 * 2016年4月30日上午8:59:48
 */
public class AccountWithSyncUsingLock_9 {
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
		private static Semaphore se = new Semaphore(1); //创建只有一个允许访问请求的信号量，数量是传入的参数
		public int getBalance() {
			return this.balance;
		}
		
		public void deposit(int amount) {
			try {
				se.acquire(); //使用的时候需要获取信号量，程序信号量就会减去1；
				int temp = this.balance + amount;
				Thread.sleep(5);
				this.balance = temp;
			}catch(Exception e) {
				throw new RuntimeException(e);
			} finally {
				se.release();//释放信号量，程序信号量会加1；
			}
		}
	}
}
