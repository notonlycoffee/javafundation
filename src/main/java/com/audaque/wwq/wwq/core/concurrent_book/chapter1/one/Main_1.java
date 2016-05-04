package com.audaque.wwq.wwq.core.concurrent_book.chapter1.one;

public class Main_1 {
	public static void main(String[] args) {
		for(int i = 0 ; i < 10 ; i++) {
			Calculator_1 c = new Calculator_1(i);
			new Thread(c).start();
		}
	}
}
