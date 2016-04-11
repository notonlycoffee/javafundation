package com.audaque.wwq.wwq.core.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.io.Writer;

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
	
	//RandomAccessFile可以写入或者读取基本类型的数据,写入的数据如果不是字符串,在文本文件中打开的就会是乱码,因为是二进制字节
	@Test
	public void demo8() {
		File file = new File("D:"+File.separator+"one.txt");
		try {
			RandomAccessFile daf = new RandomAccessFile(file, "rw");
			System.out.println(daf.getFilePointer()); //初始point位置
			daf.seek(daf.length());//将point指向文件末尾
			daf.writeBytes("asdsad");
			daf.writeInt(12);
			daf.writeBoolean(true);
			daf.writeChar('A');
			daf.writeFloat(1.21f);
			daf.writeDouble(12.123);
			System.out.println(daf.getFilePointer());
			daf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	//想文件中写入字符串,将整个字符串直接写入文件
	@Test
	public void demo9() {
		File file = new File("D:"+File.separator+"one.txt");
		OutputStream out = null;
		try {
			out = new FileOutputStream(file);
			String value = "hello";
			out.write(value.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	//想文件中写入字符串,将整个字符串一个字节一个字节地写入文件中
	@Test
	public void demo10() {
		File file = new File("D:"+File.separator+"one.txt");
		OutputStream out = null;
		try {
			out = new FileOutputStream(file);
			String value = "gotoschool";
			byte[] b = value.getBytes();
			for(int i = 0 ; i < b.length ; i++) {
				out.write(b[i]);
			}
			
			/**
			 * out.write(byte[] value, int off, int length);
			 * 
			 * out.write(value.getBytes(), 2, 5);
			 * 
			 * byte[] 是需要写入的字符串的byte数组,
			 * off是从字符串的哪里开始写入
			 * length 需要写入多少字符串,也就是写入的长度是多少
			 * 
			 */
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	//向文件中追加内容
	@Test
	public void demo11() {
		File file = new File("D:"+File.separator+"one.txt");
		OutputStream out = null;
		try {
			out = new FileOutputStream(file,true);
			out.write("\nhelo".getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	//读取文件内容,使用规定的大小读入,会有空格
	@Test
	public void demo12() {
		File file = new File("D:"+File.separator+"one.txt");
		InputStream in = null;
		try {
			in = new FileInputStream(file);
			byte [] b = new byte [1024];
			int len = in.read(b);//这里读入的长度是1024,如果实际字符串是15个长度,那剩余的就是空格
			System.out.println("读入的长度为:"+len);
			System.out.println(new String(b,0,len)); //需要指定需要的长度,不然会将多余的空格也包含进来
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	//读取文件,使用刚好足够的空间读入,不会有空格
	@Test
	public void demo13() {
		File file = new File("D:"+File.separator+"one.txt");
		InputStream in = null;
		try {
			in = new FileInputStream(file);
			byte [] b = new byte [(int) file.length()];
			in.read(b);
			System.out.println(new String(b));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//读取文件,一个字节一个字节地读入到变量中
	@Test
	public void demo14() {
		File file = new File("D:"+File.separator+"one.txt");
		InputStream in = null;
		try {
			in = new FileInputStream(file);
			byte [] b = new byte [(int) file.length()];
			for( int i = 0 ; i < file.length() ; i++) {
				b[i] = (byte) in.read();
			}
			System.out.println(new String (b));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
				
	}
	
	//在不知道文件的长度时候,进行文件的读取,当文件读取到末尾的时候返回-1
	@Test
	public void demo15() {
		File file = new File("D:"+File.separator+"one.txt");
		InputStream in = null;
		try {
			in = new FileInputStream(file);
			int len = 0;
			int count = 0;
			byte [] b = new byte[1024];
			while((len=in.read()) != -1) {
				b[count] = (byte) len;
				count++;
			}
			System.out.println(new String(b,0,count));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	//使用字符流往文件写数据,可以往后面直接添加内容
	@Test
	public void demo16() {
		File file = new File("D:"+File.separator+"one.txt");
		Writer out = null;
		try {
			out = new FileWriter(file);
//			out = new FileWriter(file,true);//可以往文件后面添加内容
			out.write("\nonetwothree");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	//使用字符流从文件中读取数据
	@Test
	public void demo17() {
		File file = new File("D:"+File.separator+"one.txt");
		Reader in = null;
		try {
			in = new FileReader(file);
			char [] c = new char [100];
			int len = in.read(c);
			System.out.println(new String(c,0,len));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	//在不知道文件大小的情况下,进行循环读取,使用字符流 
	@Test
	public void demo18() {
		File file = new File("D:"+File.separator+"one.txt");
		Reader in = null;
		try {
			in = new FileReader(file);
			int temp = 0;
			int count = 0;
			char [] c = new char[100];
			while((temp=in.read())!=-1) {
				c[count] = (char) temp;
				count++;
			}
			
			System.out.println(new String(c,0,count));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//实现文件的复制
	@Test
	public void demo19() {
		File source = new File("D:"+File.separator+"one.txt");
		File dest = new File("D:"+File.separator+"two.txt");
		
		if(!source.exists()) {
			System.out.println("文件不存在");
		}
		if(!dest.exists()) {
			try {
				dest.createNewFile();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		
	}
	
	
	
}
