package com.audaque.wwq.wwq.core.concurrent_book.chapter1.one;

/**
 * 
 *Title:
 *Description:申明实现Runnable类的任务类,将任务类传入到Thread中运行 
 *@author q
 *2016年5月4日上午10:41:07
 */
public class Main_1 {
	public static void main(String[] args) {
		for(int i = 0 ; i < 10 ; i++) {
			Calculator_1 c = new Calculator_1(i);
			new Thread(c).start();
		}
	}
}
