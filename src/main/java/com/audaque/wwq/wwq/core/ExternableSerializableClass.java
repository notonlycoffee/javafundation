package com.audaque.wwq.wwq.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

public class ExternableSerializableClass {

	public static void main(String[] args) {
		
		User2 user = new User2();
		user.setName("小张");
		user.setAge(22);
		user.setBirthday(new Date());
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream(new File("D:\\one\\twotemp")));
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
		
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new FileInputStream(new File("D:\\one\\twotemp")));
			User2 user2 = (User2) in.readObject();
			System.out.println();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		
	}

}
