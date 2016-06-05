package com.audaque.wwq.wwq.core.javahexinjishu.chapter4;

import java.util.Calendar;
import java.util.Date;

/**
 *Title:
 *Description: 
 *	由于在设计Employee类的时候，对于hireDate的getter方法返回的是克隆对象，所以
 *	不会存在进一步修改这个hireDate内容的途径和方法。所以比较安全。
 * @author jc
 * 2016年6月5日上午11:49:41
 */
public class EmployeeTest {
	public static void main(String[] args) {
		Employee e1 = new Employee("aaa",9999.0,new Date());
		
		e1.getHireDate().setMonth(Calendar.DECEMBER);
		
		System.out.println(e1);
		
		System.out.println(e1.getHireDate());
	}
}
