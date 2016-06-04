package com.audaque.wwq.wwq.core.javahexinjishu.chapter3;


/**
 * 
 *Title:
 *Description: 
 *	这个程序说明了java中unicode的增补字符使用两个代码单元，所以在length返回的不是1，而是2；
 *	对于代码点就是1，也就是我们肉眼看到的字符的个数
 *
 * @author jc
 * 2016年6月4日下午5:58:13
 */
public class TestJavaUnicode {
	public static void main(String... args) {
		char[] ch = Character.toChars(0x10400);
		System.out.printf("U+10400 高代理字符: %04x\n", (int) ch[0]);// d801
		System.out.printf("U+10400 低代理字符: %04x\n", (int) ch[1]);// dc00
		String str = new String(ch);
		System.out.println("代码单元长度: " + str.length());// 2
		System.out.println("代码点数量: " + str.codePointCount(0, str.length()));// 1
		System.out.println(str.codePointAt(0));// 返回给定位置开始或结束的代码点,66560
		System.out.println(str.charAt(1));// 返回给定位置的代码单元,由于未定义，返回?
		// 遍历一个字符串,打印出所有字符的代码点
		str += "Hello,world!";
		System.out.println("hhhhh");
		System.out.println(str);//显式这个字符𐐀Hello,world!
		System.out.println("hhhhh");
		System.out.println("next line");
		int i = 0;
		System.out.println("代码单元一共有："+str.length());
		System.out.println(str);
		
		//得到第0个代码点，这里通过offsetByCodePoints（int ,int）获取的是代码点的代码单元下标，offsetByCodePoints（0,0）获取的是第0个代码点，也就是𐐀
		//参数offsetByCodePoints（0,1）获取的是第1个代码点的下标，这里返回的是2，也就是H这个字符的代码单元下标是从2开始的，这里的内容跟下面while循环的内容差不多，因为
		//增补字符用两个代码单元，所以在使用codePointAt(index)的时候不能单纯的使用肉眼看到的位置，需要比如这里的𐐀Hello,world!，𐐀含有的位置是0到1
		//所以字符串里面的H的下标就到达了2上面去，而不是1了 
		int index = str.offsetByCodePoints(0, 0);
		System.out.println(index);
		int cpdex = str.codePointAt(index);
		System.out.println("the index to codePoint is " + cpdex);
		
		while (i < str.length()) {
			int cp = str.codePointAt(i);
			
			System.out.println(cp);
			if (Character.isSupplementaryCodePoint(cp))//确定指定的字符（Unicode代码点）是否是在增补字符范围内。
				i += 2;// 如果cp所在的位置是代码点的第一部分，执行此处
			else
				i++;
		}
		
	}
}
