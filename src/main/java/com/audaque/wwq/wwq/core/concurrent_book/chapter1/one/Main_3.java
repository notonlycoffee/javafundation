package com.audaque.wwq.wwq.core.concurrent_book.chapter1.one;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.Thread.State;

/**
 * 
 *Title:
 *Description:在Java中，线程只能有这6种中的一种状态： new, runnable, blocked, waiting, time waiting, 或 terminated.
 *线程的ID或者状态是不可修改的。
 *setPriority() 方法会抛出 IllegalArgumentException 异常，如果你设置的优先级不是在1-10之间。
 *@author q
 *2016年5月4日下午3:36:25
 */
public class Main_3 {
	
	public static void main(String[] args) {
		System.out.println(Thread.MIN_PRIORITY); //1
		System.out.println(Thread.NORM_PRIORITY);//5
		System.out.println(Thread.MAX_PRIORITY);//10
		Thread threads[] = new Thread[10];
		Thread.State status[] = new Thread.State[10];
		
		for( int i = 0 ; i < 10 ;i++) {
			threads[i] = new Thread(new Calculator_3(i));
			if(i%2==0) {
				threads[i].setPriority(Thread.MAX_PRIORITY);
			} else {
				threads[i].setPriority(Thread.MIN_PRIORITY);
			}
			threads[i].setName("Thread"+i);
		}
		
		try(FileWriter file = new FileWriter("D:"+File.separator+"logggg.txt");PrintWriter pw = new PrintWriter(file);) {
			
			for(int i = 0 ; i < 10 ; i++) {
				pw.println("Main: Status of Thread "+i+":"+threads[i].getState());//线程还没有启动,所有的线程的状态都是NEW
				status[i]=threads[i].getState();
			}
			
			for(int i = 0 ; i < 10 ; i++) {
				threads[i].start();
			}
			
			// 下面的代码一直检测线程的状态,如果线程结束就不会检测线程的状态.
			boolean finish=false;
			while (!finish) {
				for (int i=0; i<10; i++){
					if (threads[i].getState()!=status[i]) {
						writeThreadInfo(pw, threads[i],status[i]);
						status[i]=threads[i].getState();
					}
				}
				
				finish=true;
				for (int i=0; i<10; i++){
					finish=finish &&(threads[i].getState()==State.TERMINATED);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	private static void writeThreadInfo(PrintWriter pw, Thread thread, State state) {
		pw.printf("Main : Id %d - %s\n",thread.getId(),thread.getName());
		pw.printf("Main : Priority: %d\n",thread.getPriority());
		pw.printf("Main : Old State: %s\n",state);
		pw.printf("Main : New State: %s\n",thread.getState());
		pw.printf("Main : ************************************\n");
		}

}
