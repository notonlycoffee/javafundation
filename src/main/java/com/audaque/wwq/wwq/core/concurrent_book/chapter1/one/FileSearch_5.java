package com.audaque.wwq.wwq.core.concurrent_book.chapter1.one;

import java.io.File;

/***
 * 
 *Title:
 *Description: 运用程序抛出异常的方式,实现线程的中断
 *@author q
 *2016年5月4日下午5:24:10
 */
public class FileSearch_5 implements Runnable {
	
	private String initPath;
	private String fileName;
	public FileSearch_5(String initPath,String fileName) {
		this.initPath = initPath;
		this.fileName = fileName;
	}
	
	public void run() {
		File file =  new File(initPath);
		if(file.isDirectory()) {
			try {
				directoryProcess(file);
			} catch(InterruptedException  e) {
				System.out.printf("%s : The search has been interrupted",Thread.currentThread().getName());
			}
		}
	}
	
	public void directoryProcess(File file) throws InterruptedException {
		File list [] = file.listFiles();
		if(list != null) {
			for( int i = 0 ; i < list.length ; i++) {
				if(list[i].isDirectory()) {
					directoryProcess(list[i]);
				} else {
					fileProcess(list[i]);
				}
			}
		}
		if(Thread.interrupted()) {
			System.out.println(Thread.currentThread().isInterrupted());//interrupted()会将Thread的中断状态设置为false,不建议这么做
			throw new InterruptedException();
		}
	}
	
	public void fileProcess(File file) throws InterruptedException {
		if(file.getName().equals(fileName)) {
			System.out.printf("%s : %s\n",Thread.currentThread().getName(),file.getAbsolutePath());
		}
		if(Thread.interrupted()) {
			System.out.println(Thread.currentThread().isInterrupted());//interrupted()会将Thread的中断状态设置为false,不建议这么做
			throw new InterruptedException();
		}
	}
}
