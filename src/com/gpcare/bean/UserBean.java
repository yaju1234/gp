package com.gpcare.bean;

public class UserBean {
	
	private String regNo;
	private String name;
	private String  userName;
	private String dob;
	private String address;
	private String contact;
	private String emrg_contact;
	private String image;
	
	public UserBean(String regNo, String name, String userName, String dob,
			String address, String contact, String emrg_contact, String image) {
		super();
		this.regNo = regNo;
		this.name = name;
		this.userName = userName;
		this.dob = dob;
		this.address = address;
		this.contact = contact;
		this.emrg_contact = emrg_contact;
		this.image = image;
	}
	public String getRegNo() {
		return regNo;
	}
	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getEmrg_contact() {
		return emrg_contact;
	}
	public void setEmrg_contact(String emrg_contact) {
		this.emrg_contact = emrg_contact;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}	

}
