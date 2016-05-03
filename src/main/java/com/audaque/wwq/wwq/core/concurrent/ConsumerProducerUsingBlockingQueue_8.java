package com.audaque.wwq.wwq.core.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 *Title:使用阻塞队列实现线程的安全操作
 *Description: 阻塞队列包括：ArrayBlockingQueue,LinkedBlockingQueue,PriorityBlockingQueue三个类
 * @author jc
 * 2016年4月29日下午10:43:15
 */
public class ConsumerProducerUsingBlockingQueue_8 {
	
	private static ArrayBlockingQueue<Integer> buffer = new ArrayBlockingQueue<Integer>(2);
	
	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		executor.execute(new ProducerTask());
		executor.execute(new ConsumerTask());
		
		executor.shutdown();
	}
	
	public static class ProducerTask implements Runnable {
		public void run() {
			try {
				int i = 0 ; 
				while(true) {
					System.out.println("input the value is " + i);
					buffer.put(i++);  //因为是线程安全的，所以可以直接添加数据，这里会阻塞
					Thread.sleep(1000);
				}
			} catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public static class ConsumerTask implements Runnable {
		public void run() {
			try {
				while(true) {
					int value = buffer.take();//线程安全，当一个线程操作的时候会出现阻塞而不让其他线程操作
					System.out.println("take the value is " + value);
					Thread.sleep(1000);
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
