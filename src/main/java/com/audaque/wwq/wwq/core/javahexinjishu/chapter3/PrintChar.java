package com.audaque.wwq.wwq.core.javahexinjishu.chapter3;

/**
 * 
 *Title:
 *Description: 
 *	char可以将数字转化为字符变量，是根据unicode编码，对比如65这个未知的字符进行查找，这里65对应的字符是A
 * @author jc
 * 2016年6月2日下午2:29:41
 */
public class PrintChar {
	public static void main(String[] args) {
		char b = 'A';
		System.out.println(b+0);  //65
		System.out.println((char)(65)); //A
	}
}
