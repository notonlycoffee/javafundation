package com.audaque.wwq.wwq.core.reflect;

import java.io.InputStream;
import java.util.List;

public class Person {
	
	private String username;
	private String age;
	private String address;
	
	public String nn = "nnnn";
	private static int num = 123;
	private String kk = "kkkk";
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	public String getAll() {
		return "hahah";
	}
	
	
	
	public Person() {
		System.out.println("wuwuwuw");
	}
	
	public Person(String kk) {
		System.out.println(kk);
	}
	
	public Person(String kk, int aa) {
		System.out.println(kk+"  " + aa);
	}
	
	private Person(List list) {
		System.out.println("list");
	}
	
	
	
	
	
	public void aa1() {
		System.out.println("aa1");
	}
	
	public void aa1(String name,int password) {
		System.out.println(name + ":" + password);
	}
	
	public Class[] aa1(String name,int [] password) {
		return new Class[]  {String.class};
	}
	
	private void aa1(InputStream in) {
		System.out.println(in);
	}
	
	public static void aa1(int num) {
		System.out.println(num);
	}
	
	
	public static void main(String[] args) {
		System.out.println(args[0]);
	}
}
