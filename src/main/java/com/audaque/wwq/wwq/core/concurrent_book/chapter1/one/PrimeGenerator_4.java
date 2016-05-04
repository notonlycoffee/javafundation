package com.audaque.wwq.wwq.core.concurrent_book.chapter1.one;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * 
 *Title:线程的中断机制
 *Description:
 *	1.Java提供中断机制来通知线程表明我们想要结束它。中断机制的特性是线程需要检查是否被中断，
 *		而且还可以决定是否响应结束的请求。所以，线程可以忽略中断请求并且继续运行。
 *	2.Thread 类还有一个boolean类型的属性来表明线程是否被中断。当你调用线程的interrupt() 
 *		方法，就代表你把这个属性设置为 true。 而isInterrupted() 方法仅返回属性值。
 *  3.isInterrupted()和interrupted() 方法有着很重要的区别。
		第一个不会改变interrupted属性值，但是第二个会设置成false。
		interrupted() 方法是一个静态方法，建议使用isInterrupted()方法。
 *@author q
 *2016年5月4日下午4:03:08
 */
public class PrimeGenerator_4 extends Thread {
	
	public void run() {
		File file = new File("D:"+File.separator+"two.txt");
		try {
			System.setOut(new PrintStream(new FileOutputStream(file,true)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		long number = 1L;
		while(true) {
			if(isPrime(number)) {
				System.out.printf("Number %d is Prime\n",number);
			}
			if(isInterrupted()) {
				System.out.println("The Prime Generator has been Interrupted");
				return ;
			}
			number++;
		}
	}
	
	//改方法判断一个数是否为质数
	public boolean isPrime(long number) {
		  if (number <=2) {
			    return true;
			  }
			  for (long i=2; i<number; i++){
			    if ((number % i)==0) {
			       return false;
			    }
			  }
			  return true;
	}
	
}
