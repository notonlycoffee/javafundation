package com.audaque.wwq.wwq.core.concurrent_book.chapter1.one;


/**
 * 
 *Title:
 *Description:使用任务类直接继承Thread的方式,直接调用start运行线程
 *@author q
 *2016年5月4日上午10:40:13
 */
public class Main_2 {
	public static void main(String[] args) {
		for(int i = 0 ; i < 10 ; i++) {
			new Calculator_Thread_2(i).start();//
		}
	}
}
