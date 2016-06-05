package com.audaque.wwq.wwq.core.javahexinjishu.chapter4;

import java.text.NumberFormat;
import java.util.Date;

public class TestDate {
	public static void main(String[] args) {
		double num = 1.0;
		NumberFormat currency = NumberFormat.getCurrencyInstance();
		NumberFormat percent = NumberFormat.getPercentInstance();
		System.out.println(currency.format(num));
		System.out.println(percent.format(num));
		
	}
}
