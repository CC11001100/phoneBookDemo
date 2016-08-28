package org.cc.phoneBook.entity;

import java.util.List;

/**
 * 联系人模型（Model）
 * 
 * @author cc
 *
 */
public class Linkman {

	private int id;
	
	private String name;
	private String sex;
	private int age;
	private List<String> phoneNumber;
	private String qq;
	private String address;
	private String desc;
	
	@Override
	public boolean equals(Object obj) {
		//如果id相等的话就认为是同一个人
		return ((Linkman)obj).getId()==this.id;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public List<String> getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(List<String> phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setPhoneNumber() {
		// TODO Auto-generated method stub
		
	}
}
