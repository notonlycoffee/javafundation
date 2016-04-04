package com.audaque.wwq.wwq.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

public class ObjectSerializable {

	public static void main(String[] args) {
		
		User user = new User();
		user.setName("小二");
		user.setAge(22);
		user.setBirthday(new Date());
		
		File file = new File("D:\\one\\tempfile");
		ObjectOutputStream out = null;
		
		try {
			out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(user);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new FileInputStream(file));
			User user12 = (User) in.readObject();
			System.out.println();
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
}
