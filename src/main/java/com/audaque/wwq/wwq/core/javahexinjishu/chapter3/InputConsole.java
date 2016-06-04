package com.audaque.wwq.wwq.core.javahexinjishu.chapter3;

import java.io.Console;

/**
 * 
 *Title:
 *Description: 
 *	因为使用Scanner类在控制台进行输入的时候是显式输入的，输入密码也是会显示出来，所以这里使用另外一个类进行输入，输入的时候不会
 *	显示密码的内容，但是：以Javaw所执行的应用程式（eclipse）没有主控制台（console），所以取不到console物件，System.console()只能是null了。
 *	所以这个方式的输入只能用在控制窗口，比如window的dos窗口上面。
 * @author jc
 * 2016年6月4日下午12:14:11
 */
public class InputConsole {
	public static void main(String[] args) {
		Console cons = System.console();
		System.out.println(cons);
		String username = cons.readLine("input the name:");
		char [] password = cons.readPassword("input the password :");
		
		System.out.println(username);
		System.out.println(password);
		
	}
}
