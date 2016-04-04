package com.audaque.wwq.wwq.core.introspector;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

import org.junit.Test;

/**
 * 
 * @author q
 *
 *javaBean是一个类中有多少get或者set方法,就有多少个属性,比如一个类Person里面含有
 *setName();	getAge();这两个方法,但是没有name和age这两个字段,那还是有两个属性,但是总体来说
 *这个Person类里面一共就有三个属性,因为所有的类都继承Object类;Object类里面有一个getClass方法,
 *所以会在所有的子类中多一个属性,就是getClass()
 *
 *
 */
public class IntrospectorDemo {

	
	//使用内省操纵bean的属性
	@Test
	public void demo1() {
		
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(Person.class);
			PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
			for(PropertyDescriptor pd : pds) {
				String methodName = pd.getName();
				System.out.println(methodName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//操纵bean指定的属性值
	@Test
	public void demo2() {
		Person p = new Person();
		try {
			PropertyDescriptor pd = new PropertyDescriptor("name", Person.class);
			//操纵set方法
			Method me = pd.getWriteMethod();
			me.invoke(p, "nini");
			System.out.println(p.getName());
			
			//操纵get方法
			Method readMethod = pd.getReadMethod();
			System.out.println(readMethod.invoke(p, null));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//获取当前操纵的属性的类型
	@Test
	public void demo3() {
		try {
			PropertyDescriptor pd = new PropertyDescriptor("name", Person.class);
			Class clazz = pd.getPropertyType();
			System.out.println(clazz);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
