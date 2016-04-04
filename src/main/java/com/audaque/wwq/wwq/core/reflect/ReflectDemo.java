package com.audaque.wwq.wwq.core.reflect;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ReflectDemo {
	
	
	
	@Test
	public void demo1() {
		//加载类的字节码,这里使用同一个包下面的Person类;
		try {
			
		//1
		Class clazz = Class.forName("com.audaque.wwq.wwq.core.reflect.Person");
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//2
		Class clazz1 = new Person().getClass();
		
		//3
		Class clazz2 = Person.class;
	}
	
	
	/***********************反射构造方法****************************************************************/
	
	//反射类的无参构造方法:public Person();
	@Test
	public void demo2() {
		try {
			
			Class clazz = Class.forName("com.audaque.wwq.wwq.core.reflect.Person");
			Constructor c = clazz.getConstructor(null);
			Person p = (Person) c.newInstance(null);
			System.out.println(p.nn);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//反射类的有参构造方法:public Person(String kk);
	@Test
	public void demo3() {
		try {
			Class clazz = Class.forName("com.audaque.wwq.wwq.core.reflect.Person");
			Constructor c = clazz.getConstructor(String.class);//参数是类的字节码
			Person p = (Person) c.newInstance("name");
			System.out.println(p.nn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//反射类的有参构造方法(含有多个参数):public Person(String kk, int aa);
	@Test
	public void demo4() {
		try {
			Class clazz = Class.forName("com.audaque.wwq.wwq.core.reflect.Person");
			Constructor c = clazz.getConstructor(String.class,int.class);
			Person p = (Person) c.newInstance("name",12);
			System.out.println(p.nn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//反射类的私有构造方法:private Person(List list);
	//调用的是getDeclaredConstructor(...)方法,同时需要使用setAccessible(true);方法设置可以访问
	@Test
	public void demo5() {
		try {
			Class clazz = Class.forName("com.audaque.wwq.wwq.core.reflect.Person");
			Constructor c = clazz.getDeclaredConstructor(List.class);
			c.setAccessible(true);
			Person p = (Person)c.newInstance(new ArrayList());
			System.out.println(p.nn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//反射类的无参构造方法的另一种方式:
	//直接用Class的newInstance()方法是调用类的无参构造方法
	@Test
	public void demo5_1() {
		try {
			Class clazz = Class.forName("com.audaque.wwq.wwq.core.reflect.Person");
			Person p = (Person)clazz.newInstance();
			System.out.println(p.nn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/***********************反射类方法****************************************************************/
	
	
	//反射公共的无参方法:public void aa1()
	@Test
	public void demo6() {
		Person p = new Person();
		try {
			Class clazz = Class.forName("com.audaque.wwq.wwq.core.reflect.Person");
			Method m = clazz.getMethod("aa1", null);
			m.invoke(p, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//反射公共的有参方法:public void aa1(String name,int password);
	@Test
	public void demo7() {
		Person p = new Person();
		try {
			Class clazz = Class.forName("com.audaque.wwq.wwq.core.reflect.Person");
			Method m = clazz.getMethod("aa1", String.class,int.class);
			m.invoke(p, "name",12);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//反射公共的有参方法,并且是有返回值的:public Class[] aa1(String name,int [] password);
	//这里我们之后方法调用之后是返回什么值,就可以直接写
	@Test
	public void demo8() {
		Person p = new Person();
		try {
			Class clazz = Class.forName("com.audaque.wwq.wwq.core.reflect.Person");
			Method m = clazz.getMethod("aa1", String.class,int[].class);
			Class[] cs  = (Class[]) m.invoke(p, "name",new int [] {1,2,3});
			System.out.println(cs[0]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//反射私有的方法private void aa1(InputStream in) 
	//因为是FileInputSteam,这里的1.txt是必须在c盘目录下面存在的
	@Test
	public void demo9() {
		Person p = new Person();
		try {
			Class clazz = Class.forName("com.audaque.wwq.wwq.core.reflect.Person");
			Method m = clazz.getDeclaredMethod("aa1", InputStream.class);
			m.setAccessible(true);
			m.invoke(p, new FileInputStream(new File("c:\\1.txt")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//反射静态的方法public static void aa1(int num)
	@Test
	public void demo10() {
		try {
			Class clazz = Class.forName("com.audaque.wwq.wwq.core.reflect.Person");
			Method m = clazz.getMethod("aa1", int.class);
			m.invoke(null, 12);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//反射主方法:public static void main(String[] args);
	//使用m.invoke(null, new String []{"1","2"});是会导致异常的,异常信息是:java.lang.IllegalArgumentException: wrong number of arguments
	/*
	 分析原因:
	 	jdk 1.4  m.invoke(null,Object object[]);
	 	jdk 1.5 开始  m.invoke(null,Class....args);这里的参数是Class...args,也就是可变从参数
	 	
	 	假设调用的方法是:a(String name,String password);
	 	则在1.4传入的是m.invoke(null,String str[]{"dd","12"}),然后会将String数据里面的元素拆出来,逐个分给方法的参数,也就是dd分给方法的name;
	 		12分给方法的passord;所以这里1.5要兼容1.4的运行内容,在1.5里面的传入参数也需要进行拆分之后进行分配,所以在main主方法中,我们的方法调用是:
	 		m.invoke(null, new String []{"1","2"});那就会拆掉数据逐个分给方法,就相当于是main(String str1,String str2);但是main方法的正确格式是:
	 		main(Stringn [] args);所以这里报错的内容是参数个数不正确
	 
	 解决方法:
	 	1.因为传入的数组会进行一重拆分,所以这里我们用另外一个数组将参数套住,拆分之后的的内容还是一个数组,就不会有错:
	 		m.invoke(null, new Object[] {new String[]{"1","2"}});
	 	2.既然会对数据进行拆分,那么我们传入的参数就不要声明为数据,也就是用一个Object类型将数组进行包装:
	 		m.invoke(null, (Object)new String[]{"1","2"});
	 * */
	@Test
	public void demo11() {
		try {
			Class clazz = Class.forName("com.audaque.wwq.wwq.core.reflect.Person");
			Method m = clazz.getMethod("main", String[].class);
//			m.invoke(null, new String []{"1","2"});  //error
//			m.invoke(null, new Object[] {new String[]{"1","2"}});
			m.invoke(null, (Object)new String[]{"1","2"});
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/***********************反射字段****************************************************************/
	//反射公有的字段:public String nn = "nnnn";
	@Test
	public void demo12() {
		Person p = new Person();
		try {
			Class clazz = Class.forName("com.audaque.wwq.wwq.core.reflect.Person");
			Field f = clazz.getField("nn");
			String value = (String) f.get(p);
			System.out.println(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//反射公有的字段::public String nn = "nnnn";写法更加严谨一点的:
	//反射得到的字段并不知道详细的类型,所以可以调用Field.getType()获取相应类型的字节码类
	@Test
	public void demo13() {
		Person p = new Person();
		try {
			Class clazz = Class.forName("com.audaque.wwq.wwq.core.reflect.Person");
			Field f = clazz.getField("nn");
			Class type = f.getType();
			if(type.equals(String.class)) {
				System.out.println(f.get(p));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//反射私有字段:private String kk = "kkkk";
	//反射私有字段需要调用setAccessible设置访问
	@Test
	public void demo14() {
		Person p = new Person();
		try {
			Class clazz = Class.forName("com.audaque.wwq.wwq.core.reflect.Person");
			Field f = clazz.getDeclaredField("kk");
			f.setAccessible(true);
			Class type = f.getType();
			if(type.equals(String.class)) {
				System.out.println(f.get(p));
				f.set(p, "dd");
				System.out.println(f.get(p));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//反射静态字段:private static int num = 123;
	@Test
	public void demo15() {
		try {
			Class clazz = Class.forName("com.audaque.wwq.wwq.core.reflect.Person");
			Field f = clazz.getDeclaredField("num");
			f.setAccessible(true);
			Class<?> type = f.getType();
			if(type.equals(int.class)) {
				System.out.println(f.get(null));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
