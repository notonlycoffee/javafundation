package com.audaque.wwq.wwq.core.concurrent;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 *Title: 消费者和生产者问题
 *Description: 
 * @author jc
 * 2016年4月28日下午10:27:46
 */
public class ConsumerProducer_7 {
	private static BufferTemp buffer = new BufferTemp();
	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		executor.execute(new WriteTask());
		executor.execute(new ReadTask());
		executor.shutdown();
	}
	
	private static class WriteTask implements Runnable {
		public void run() {
			try {
				int i = 0;
			while(true) {
				System.out.println("write number" + i);
				buffer.write(i++);
				Thread.sleep(1000);
			}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private static class ReadTask implements Runnable {
		public void run() {
			try {
			while(true) {
				int value = buffer.read();
				System.out.println("read the value:"+value);
				Thread.sleep(1000);
			}
			} catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	private static class BufferTemp {
		private static final int CAPACITY = 1;
		private LinkedList<Integer> queue = new LinkedList<Integer>();
		
		private Lock lock = new ReentrantLock();
		private Condition notFull = lock.newCondition();
		private Condition notEmpty = lock.newCondition();
		
		public int read() {
			lock.lock();
			int value = 0;
				try {
					while(queue.isEmpty()) {
						System.out.println("queue is empty");
					notEmpty.await();
					}
					value = queue.remove();
					notFull.signal();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			} finally {
				lock.unlock();
				return value;
			}
			
		}
		
		public void write(int va) {
			lock.lock();
			try {
			while(queue.size()==CAPACITY) {
				System.out.println("queue is not empty");
				notFull.await();
			}
			queue.offer(va);
			notEmpty.signal();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
			
		}
	}
	
}
