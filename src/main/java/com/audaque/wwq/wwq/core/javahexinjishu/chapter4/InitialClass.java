package com.audaque.wwq.wwq.core.javahexinjishu.chapter4;


/**
 * 
 *Title:
 *Description: 
 *	成员变量数据的赋值顺序是：初始化块赋值--->构造器赋值
 * @author jc
 * 2016年6月10日下午3:05:20
 */
public class InitialClass {
	
	private String id;
	private long age;
	
	
	 {//这里是初始化块
		id="12";
		age = 24;
	}
	 
	 
	 public InitialClass() {//这里是构造器
		 id="45";
		 age = 9;
	 }
	 
	 public static void main(String[] args) {
		InitialClass c = new InitialClass();
		System.out.println(c.getId()+"  "+c.getAge());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getAge() {
		return age;
	}

	public void setAge(long age) {
		this.age = age;
	}
	 
	 
}
