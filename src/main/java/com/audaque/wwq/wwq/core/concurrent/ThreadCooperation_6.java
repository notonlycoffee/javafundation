package com.audaque.wwq.wwq.core.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;



/**
 * 线程之间的协作通信,使用Condition条件,await()让线程等待,signalAll()唤醒所有的等待线程
 * @author q
 *
 */
public class ThreadCooperation_6 {
	private static Account account = new Account();
	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		executor.execute(new DepositTask());
		executor.execute(new WithDrawTask());
		
	}
	
	
	private static class DepositTask implements Runnable {
		public void run() {
			while(true) {
				try {
					account.deposit((int)(Math.random()*10+1));
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
	
	private static class WithDrawTask implements Runnable {
		public void run() {
			while(true) {
				try {
				account.withDraw(((int)(Math.random()*10+1)));
				Thread.sleep(1000);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	private static class Account {
		private int balance;
		private static Lock lock = new ReentrantLock();
		private static Condition newDeposit = lock.newCondition();  //申明一个条件,使用之前必须先获取锁,不然会报错
		
		public int getBalance() {
			return this.balance;
		}
		
		public void deposit(int amount) {
			lock.lock();
			try {
				int temp = this.balance + amount;
				this.balance = temp;
				
				System.out.println("添加数量:" + amount + "   添加之后:" + this.balance);
				newDeposit.signalAll();   //唤醒所有的等待线程,是newDeposit这个条件等待的所有线程
				
			} catch(Exception e) {
				throw new RuntimeException(e);
			}finally {
				lock.unlock();
			}
		}
		
		public void withDraw(int amount) {
			lock.lock();
			try {
				System.out.println("需要取出" + amount+"    存款有:"+this.getBalance());
				while(this.getBalance()<amount) {
					System.out.println("wait for deposit");
					newDeposit.await();   //让线程等待
				}
				this.balance -= amount;
				System.out.println("取出之后的钱还剩下" + this.balance+"\n");
			} catch(Exception e) {
				throw new RuntimeException(e);
			}finally {
				lock.unlock();
			}
		}
		
		
	}
}
