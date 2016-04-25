package com.audaque.wwq.wwq.core;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;
import java.util.Date;

public class User implements Serializable,Externalizable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3471393535267840054L;
	private transient String name;  //使用了transient，不能序列化
	private int age;
	private Date birthday;
	
//	public User() {
//		
//	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public void readExternal(ObjectInput in) throws IOException,
			ClassNotFoundException {
		this.age = in.readInt();
		this.name = in.readUTF();
		
	}
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeInt(this.age);
		out.writeUTF(this.name);
	}
}
