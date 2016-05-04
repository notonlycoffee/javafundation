package com.audaque.wwq.wwq.core.concurrent_book.chapter1.one;

public class Main_2 {
	public static void main(String[] args) {
		for(int i = 0 ; i < 10 ; i++) {
			new Caculator_Thread_2(i).start();;
		}
		
	}
}
