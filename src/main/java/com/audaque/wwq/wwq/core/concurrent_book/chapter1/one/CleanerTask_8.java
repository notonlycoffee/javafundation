package com.audaque.wwq.wwq.core.concurrent_book.chapter1.one;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Date;
import java.util.Deque;


public class CleanerTask_8 extends Thread {
	private Deque<Event_8> deque;
	public CleanerTask_8(Deque<Event_8> deque) {
		this.deque = deque;
		setDaemon(true);//只能在start() 方法之前可以调用 setDaemon() 方法。一旦线程运行了，就不能修改守护状态。
	}

	@Override
	public void run() {
		while (true) {
			Date date = new Date();
			clean(date);
		}
	}

	private void clean(Date date) {
		long difference;
		boolean delete;
		if (deque.size()==0) {
			return;
		}
		delete=false;
		do {
			Event_8 e = deque.getLast();
			difference = date.getTime() - e.getDate().getTime();
			if (difference > 10000) {
				System.out.printf("Cleaner: %s\n",e.getEvent());
				deque.removeLast();
				delete=true;
			}	
		} while (difference > 10000);
		if (delete){
			System.out.printf("Cleaner: Size of the queue: %d\n",deque.size());
		}
	}
}
