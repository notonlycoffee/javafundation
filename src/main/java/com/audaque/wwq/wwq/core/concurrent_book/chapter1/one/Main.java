package com.audaque.wwq.wwq.core.concurrent_book.chapter1.one;

public class Main {
	public static void main(String[] args) {
		for(int i = 0 ; i < 10 ; i++) {
			Calculator c = new Calculator(i);
			new Thread(c).start();
		}
	}
}
