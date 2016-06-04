package com.audaque.wwq.wwq.core.javahexinjishu;

/**
 * 
 *Title:
 *Description: 
 *		java的变量规定只能由字母开头，并且是子母和数字的序列都可以，这里字母一般说的是A~Z和a~z以及“_”下划线和“$”，但是
 *	java对于字母的范围还是很大的，这里对于字母的定义不仅仅是这些，这里的字母是java定义在unicode中可以表示字母的的字符，使用
 *	Character.isJavaIdentifierStart(char ch)可以区分某个unicode字符是否可以作为java变量的开头
 *	Character.isJavaIdentifierPart(char ch)可以区分某个unicode字符是否可以作为java变量的一部分，不仅仅是开头；
 *	所以在这里得出的结果是我们可以命名一个变量，比如：
 *		String str = "hello world"; 也可以命名一个变量，比如：
 *		String ĕ = "hello world";
 *		
 * @author jc
 * 2016年6月2日下午7:06:58
 */
public class IsLetter {
	private static int startNum = 0;
	private static int partNum = 0;
	public static void isLetter() {
		
		
		System.out.println("可以作为开头的字符");
		
		for( int i = 0 ; i < 500 ; i++) {
			char a = 0;
			boolean istrue = false;
			char at = (char)(a+i);
			Character c = new Character(at);
			if(Character.isJavaIdentifierStart(c)) {//判断字符是否可以作为java变量的首个字符
				System.out.print(c+"  ");
				if(i % 15 == 0) {
					System.out.println();
				}
				istrue=true;
			} else {
				istrue=false;
			}
			
			if(istrue) {
				startNum++;
			}
		}
		
		System.out.println("\n\n不可以作为开头的：");
		for( int i = 0 ; i < 500 ; i++) {
			char a = 0;
			char at = (char)(a+i);
			Character c = new Character(at);
			if(Character.isJavaIdentifierPart(c)) {//判断字符是否可以作为java变量的一部分
				if(!Character.isJavaIdentifierStart(c)) {
					System.out.print(c+"  ");
					if(i % 15 == 0) {
						System.out.println();
					}
				}
			} 
		}
		
		System.out.println("\n\n可以作为变量一部分的：");
		for( int i = 0 ; i < 500 ; i++) {
			char a = 0;
			boolean istrue = false;
			char at = (char)(a+i);
			Character c = new Character(at);
			if(Character.isJavaIdentifierPart(c)) {//判断字符是否可以作为java变量的一部分
					System.out.print(c+"  ");
					if(i % 15 == 0) {
						System.out.println();
					}
					istrue = true;
				
			} else {
				istrue = false;
			}
			if(istrue) {
				partNum++;
			}
		}
	}
	
	public static void main(String[] args) {
		IsLetter.isLetter();
		System.out.println();
		System.out.println("\n\ncan start number is : "+IsLetter.startNum);
		System.out.println("can be part of it is : "+IsLetter.partNum);
	}
}
