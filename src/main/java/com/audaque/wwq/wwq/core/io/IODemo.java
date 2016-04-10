package com.audaque.wwq.wwq.core.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import org.junit.Test;

public class IODemo {
	
	
	//创建文件
	@Test
	public void demo1() {
		File file = new File("D:\\one.txt");
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("file is already exit");
		}
		
	}
	
	//利用常量文件分割符创建文件,实现window和linux的文件路径跨平台,如果文件已经存在则删除文件
	//window文件分割符为"\" linux中文件分隔符为"/"
	//window中路径分隔符为分号";"   linux中的路径分隔符为冒号":"
	@Test
	public void demo2() {
		System.out.println(File.separator);
		System.out.println(File.pathSeparator);
		String fileName = "D:" + File.separator+"one.txt";
		File file = new File(fileName);
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			file.delete();
			System.out.println("delete file");
		}
	}
	
	//创建一个文件夹
	@Test
	public void demo3() {
		String filePath = "D:"+File.separator+"one";
		File file = new File(filePath);
		file.mkdir();
	}
	
	
	//列出指定目录的全部文件（包括隐藏文件）：
	@Test
	public void demo4() {
		File file  = new File("D:" + File.separator+"医疗数据");
		String [] fileName = file.list();
		for(String name : fileName ) {
			System.out.println(name);
		}
	}
	
	//列出指定目录的全部文件(包括隐藏文件),路径为全路径
	@Test
	public void demo5() {
		File file = new File("D:"+File.separator+"医疗数据");
		File files [] = file.listFiles();
		for(File fileObject : files) {
			System.out.println(fileObject.getPath());
		}
	}
	
	
	@Test
	public void demo6() {
		File file = new File("D:"+File.separator+"医疗数据");
		printFile(file);
	}
	
	
	public void printFile(File file) {
		
		if(file != null) {
			if(file.isDirectory()) {
				File files [] = file.listFiles();
				for(File fileObject:files) {
					printFile(fileObject);
				}
			} else {
				System.out.println(file.getPath());
			}
		}
		
		
	}
	
	
	//使用RandomAccessFile写入文件
	//RandomAccessFile类可以实现文件的追加和任意位置的读写功能,包括读取和写入功能
	@Test
	public void demo7() {
		File file = new File("D:"+File.separator+"one.txt");
		try {
			RandomAccessFile daf = new RandomAccessFile(file, "rw");
			System.out.println(daf.getFilePointer()); //初始point位置
			daf.seek(daf.length());//将point指向文件末尾
			daf.writeBytes("\nhello my friend");//在末尾写入数据
			System.out.println(daf.getFilePointer());
			daf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
}
