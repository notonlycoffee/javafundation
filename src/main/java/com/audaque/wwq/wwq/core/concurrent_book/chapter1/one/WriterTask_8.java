package com.audaque.wwq.wwq.core.concurrent_book.chapter1.one;

import java.util.Date;
import java.util.Deque;
import java.util.concurrent.TimeUnit;


public class WriterTask_8 implements Runnable {
	Deque<Event_8> deque;  //Deque 是双向队列
	public WriterTask_8(Deque<Event_8> deque){
		this.deque=deque;
	}
	
	@Override
	public void run() {
		try {
			for (int i=1; i<100; i++) {
				Event_8 event=new Event_8();
				event.setDate(new Date());
				event.setEvent(String.format("The thread %s has generated an event",Thread.currentThread().getId()));
				deque.addFirst(event);
				TimeUnit.SECONDS.sleep(1);
				}
			} catch(InterruptedException e) {
					e.printStackTrace();
				}
	}
	
}
