package com.audaque.wwq.wwq.core.another_concurrentbook.chapter3;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueDemo {
	public static void main(String[] args) {
		BlockingQueue<String> queue = new ArrayBlockingQueue<String>(5);
		Producer p = new Producer(queue);
		Consumer c1 = new Consumer(queue);
		Consumer c2 = new Consumer(queue);
		new Thread(p).start();
		new Thread(c1).start();
		new Thread(c2).start();
	}
	
	static class Producer implements Runnable {
		private BlockingQueue<String> queue;
		public Producer(BlockingQueue<String> q) {
			queue = q;
		}
		public void run() {
			try {
			for( int i = 0 ; i < 100 ; i++) {
				queue.put(produce());
			}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		String produce() {
			String temp = "" + (char)('A'+(int)(Math.random()*26));
			System.out.println("produce " + temp);
			return temp;
		}
	}
	
	static class Consumer implements Runnable {
		private BlockingQueue<String> queue;
		public Consumer(BlockingQueue<String> q) {
			queue = q;
		}
		public void run() {
			try {
			for(int i = 0 ; i < 100 ; i++) {
				consume(queue.take());
			}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		void consume(String s) {
			System.out.println("consume " + s);
		}
		
		
	}
}
