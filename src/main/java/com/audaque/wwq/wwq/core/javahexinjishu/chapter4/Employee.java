package com.audaque.wwq.wwq.core.javahexinjishu.chapter4;

import java.util.Date;

/**
 *Title:
 *Description: 
 *	这里的要说明问题是hireDate的getter方法返回的是对于这个Date的克隆对象，而不是直接返回Date对象本身
 *	原因是这个hireDate的getter返回的date对象是一个类，是一个可变对象，所以在返回这个Date对象的实例之后
 *	我们虽然没有提供对于hireDate的setter方法，但是还是可以直接修改这个变量的在堆内存中的值，所以必须
 *	返回克隆对象。
 * @author jc
 * 2016年6月5日上午11:49:15
 */
public class Employee implements Cloneable {
	private String name;
	private double salary;
	private Date hireDate;
	
	@Override
	public String toString() {
		return "Employee [name=" + name + ", salary=" + salary + ", hireDate="
				+ hireDate + "]";
	}
	public Employee(String name, double salary, Date hireDate) {
		super();
		this.name = name;
		this.salary = salary;
		this.hireDate = hireDate;
	}
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Date getHireDate() {
		return (Date) hireDate.clone();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	
}
