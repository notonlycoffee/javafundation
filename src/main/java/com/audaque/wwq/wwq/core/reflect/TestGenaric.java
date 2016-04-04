package com.audaque.wwq.wwq.core.reflect;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public class TestGenaric {

	public static void main(String[] args) {
		
		List<String> list = new ArrayList<String>();
		System.out.println(list.getClass());
		ParameterizedType pt = (ParameterizedType) list.getClass().getGenericSuperclass();
		;
		
		System.out.println(pt.getActualTypeArguments()[0]);
	}
	
}
