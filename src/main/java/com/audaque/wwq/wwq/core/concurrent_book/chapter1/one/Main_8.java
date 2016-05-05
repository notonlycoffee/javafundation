package com.audaque.wwq.wwq.core.concurrent_book.chapter1.one;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.LinkedBlockingDeque;


/**
 * 
 *Title:
 *Description:
 *	1.Java有一种特别的线程叫做守护线程。这种线程的优先级非常低，
 *		通常在程序里没有其他线程运行时才会执行它。当守护线程是程序里唯一在运行的线程时，JVM会结束守护线程并终止程序。
 *	2.守护线程通常用于在同一程序里给普通线程（也叫使用者线程）提供服务。
 *	3.它们不能做重要的任务，因为我们不知道什么时候会被分配到CPU时间片，
 *		并且只要没有其他线程在运行，它们可能随时被终止。JAVA中最典型的这种类型代表就是垃圾回收器。
 *@author q
 *2016年5月5日下午2:56:56
 */
public class Main_8 {
	public static void main(String[] args) {
		Deque<Event_8> deque=new LinkedBlockingDeque<Event_8>();
		WriterTask_8 writer=new WriterTask_8(deque);
		for (int i=0; i<3; i++){
			Thread thread=new Thread(writer);
			thread.start();
		}
		CleanerTask_8 cleaner=new CleanerTask_8(deque);
		cleaner.start();

	}

}
