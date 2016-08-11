package org.cc.foo;

/**
 * 联系人模型(主键：name) （Model）
 * 
 * @author cc
 *
 */
public class Linkman {

	private String name;
	private String sex;
	private int age;
	private String phoneNumber;
	private String qq;
	private String address;

	@Override
	public String toString() {
		
		StringBuilder sb=new StringBuilder();
		
		sb.append("\t姓名:").append(name).append("\n");
		sb.append("\t性别:").append(sex).append("\n");
		sb.append("\t年龄：").append(age).append("\n");
		sb.append("\t电话:").append(phoneNumber).append("\n");
		sb.append("\t扣扣：").append(qq).append("\n");
		sb.append("\t地址：").append(address).append("\n");
		
		return sb.toString();
//		
//		return "Linkman [name=" + name + ", sex=" + sex + ", age=" + age + ", phoneNumber=" + phoneNumber + ", qq=" + qq
//				+ ", address=" + address + "]";
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
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

}
