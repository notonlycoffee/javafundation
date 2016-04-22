package com.audaque.wwq.wwq.core.io;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PushbackInputStream;
import java.io.SequenceInputStream;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.junit.Test;

import com.audaque.wwq.wwq.core.User;

public class IODemo1 {
	
	//输出重定向,重定向在这里就是: 
	/**
	 * System.out.println()本来是向控制台输出内容
	 * System.setOut()将输出重定向到文件中
	 * 
	 */
	@Test
	public void demo() {
		System.out.println("这些内容在控制台打印出来");
		File file = new File("D:"+File.separator+"one.txt");
		try {
			System.setOut(new PrintStream(new FileOutputStream(file)));  //将输出重定向到文件中
			System.out.println("这些内容在文件中打印出来"); //只会在文件中打印出来,而不会在控制台打印出来
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	//输出重定向可以用来保存错误信息到文件中
	@Test
	public void demo1() {
		System.err.print("这里是控制台出现的错误信息");
		File file = new File("D:"+File.separator+"one.txt");
		try {
			System.setErr(new PrintStream(new FileOutputStream(file)));
			System.err.print("这里是文件中出现的错误信息");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	//输入重定向,直接从文件中读入内容
	@Test
	public void demo2() {
		File file = new File("D:"+File.separator+"one.txt");
		try {
			System.setIn(new FileInputStream(file));
			int len = 0;
			byte b [] = new byte[1024];
			len = System.in.read(b);
			System.out.println(new String(b,0,len));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//将从键盘读入的输入System.in这个字节流转换为字符流
	@Test
	public void demo3() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String value = null;
		try {
			value = reader.readLine();
			System.out.println(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//使用Scanner类进行输入操作
	@Test
	public void demo4() {
		Scanner s = new Scanner(System.in);
		int value = s.nextInt();
		System.out.println(value);
	}
	
	
	
	//使用Scanner类进行输入,从文件输入到控制台
	@Test
	public void demo5() {
		File file = new File("D:"+File.separator+"one.txt");
		try {
			Scanner s = new Scanner(file);
			String value = s.next();
			System.out.println(value);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	
	//使用DataOutputStream
	@Test
	public void demo6() {
		File file = new File("D:"+File.separator+"one.txt");
		DataOutputStream out = null;
		try {
			out = new DataOutputStream(new FileOutputStream(file));
			char [] cs = {'A','B','C'};
			for(char c : cs) {
				out.writeChar(c);
			}
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
	
	
	//使用DataInputStream
	@Test
	public void demo7() {
		File file = new File("D:"+File.separator+"one.txt");
		InputStream in = null;
		try {
			in = new DataInputStream(new FileInputStream(file));
			int len = 0;
			int temp = 0;
			while((temp=in.read())!= -1) {
				char value = (char) temp;
				System.out.println(value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//合并流,将两个流合并在一起写入到另外一个流中
	@Test
	public void demo8() {
		File file1 = new File("D:"+File.separator+"one.txt");
		File file2 = new File("D:"+File.separator+"two.txt");
		File file3 = new File("D:"+File.separator+"three.txt");
		InputStream in1 = null;
		InputStream in2 = null;
		OutputStream out = null;
		try {
			in1 = new FileInputStream(file1);
			in2 = new FileInputStream(file2);
			out = new FileOutputStream(file3);
			
			SequenceInputStream se = new SequenceInputStream(in1, in2);//按照顺序:in1,in2写入文件中
			int len = 0;
			int temp = 0;
			
			while((temp=se.read())!=-1) {
				out.write(temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				in1.close();
				in2.close();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	//压缩单个文件
	@Test
	public void demo9() {
		File file = new File("D:"+File.separator+"one.txt");
		File zipFile = new File("D:"+File.separator+"one.zip");
		InputStream in = null;
		ZipOutputStream out = null;
		try {
			in = new FileInputStream(file);
			out = new ZipOutputStream(new FileOutputStream(zipFile));
			out.putNextEntry(new ZipEntry("hh.txt")); //设置在压缩文件里面的实体,包括实体的文件名
			out.setComment("hello.txt");
			int temp = 0;
			while((temp=in.read())!=-1) {
				out.write(temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	
	
	//压缩多个文件
	@Test
	public void demo10() {
		File file = new File("D:"+File.separator+"one");
		File zipFile = new File("D:"+File.separator+"one.zip");
		
		try {
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFile));
			out.setComment("hello");
			if(file.isDirectory()) {
				for(File fileObject: file.listFiles()) {
					if(fileObject.isFile()) {
						InputStream in = new FileInputStream(fileObject);
						out.putNextEntry(new ZipEntry(fileObject.getName()));
						
						int temp = 0;
						while((temp=in.read())!=-1) {
							out.write(temp);
						}
						in.close();
					}
				}
			}
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
	}
	
	
	
	
	//解压单个文件
	@Test
	public void demo11() {
		File source = new File("D:"+File.separator+"one.zip");
		File dest = new File("D:"+File.separator+"upZip.txt");
		InputStream in = null;
		OutputStream out = null;
		try {
			ZipFile zipFile = new ZipFile(source);
			ZipEntry entry = zipFile.getEntry("hh.txt");
			in = zipFile.getInputStream(entry);
			out = new FileOutputStream(dest);
			int temp = 0;
			while((temp=in.read())!=-1) {
				out.write(temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	
	//解压多个文件,主要是要得到每个文件的entry,然后根据调用zipFile.getInputStream(entry)就可以获取文件的输入流,再输出到指定文件就解压成功了
	//要得到entry就需要使用ZipInputStream类,将需要解压的目标文件传入作为参数
	//ZipInputStream zipInput = new ZipInputStream(new FileInputStream(source));
	@Test
	public void demo12() {
		
		File source = new File("D:"+File.separator+"one.zip");
		File dest = null;
		InputStream in = null;
		OutputStream out = null;
		ZipEntry entry = null;
		ZipFile zipFile = null;
		try {
			zipFile = new ZipFile(source);
			ZipInputStream zipInput = new ZipInputStream(new FileInputStream(source));
			while((entry=zipInput.getNextEntry())!=null) {
				System.out.println("要解压的文件是"+entry.getName());
				dest = new File("D:"+File.separator+"one"+File.separator+entry.getName());
				
				if(!dest.getParentFile().exists()) {
					dest.getParentFile().mkdir();
				}
				if(!dest.exists()) {
					dest.createNewFile();
				}
				out = new FileOutputStream(dest);
				in = zipFile.getInputStream(entry);
				int temp = 0;
				while((temp=in.read())!=-1) {
					out.write(temp);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	//回退流
	@Test
	public void demo13() {
		File file = new File("D:"+File.separator+"one.txt");
		PushbackInputStream in = null;
		try {
			in = new PushbackInputStream(new FileInputStream(file));
			int temp = 0;
			int count = 0;
			byte [] b = new byte[1024];
			while((temp=in.read())!=-1) {
				System.out.println(temp);
				if((temp) == '3') {
					in.unread(temp);
					temp = in.read();
					System.out.print("回退"+(char)temp+"\n");
				} else {
					b[count] = (byte) temp;
					count++;
				}
				
			}
			System.out.println();
			System.out.println(new String(b,0,count));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Test
	public void demo14() {
		File file = new File("D:"+File.separator+"one.txt");
		OutputStream out = null;
		try {
			out = new FileOutputStream(file);
			byte [] bytes = "你好".getBytes("ISO8859-1");
			out.write(bytes);
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
	
	
	
	@Test
	public void demo15() {
		User user = new User();
		user.setAge(12);
		user.setName("李白");
		
		File file = new File("D:"+File.separator+"one.txt");
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(user);
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
	
	
	@Test
	public void demo16() {
		File file = new File("D:"+File.separator+"one.txt");
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new FileInputStream(file));
			User user = (User) in.readObject();
			int age = user.getAge();
			String name = user.getName();//显示为null，因为User中对Name字段设置了transient
			
			System.out.println(age+"  "+ name);
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
	
	
	/**
	 * 当一个类要使用Externalizable这个接口的时候，
	 * 这个类中必须要有一个无参的构造函数，如果没有的话，
	 * 在构造的时候会产生异常，这是因为在反序列话的时候会默认调用无参的构造函数。
	 */
	@Test
	public void demo17() {
		
	}
	
	
	
	
	
	
	
	
	
}
