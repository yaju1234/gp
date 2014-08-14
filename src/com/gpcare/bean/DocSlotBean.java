package com.gpcare.bean;

public class DocSlotBean {
	
	private String name;
	private String Time;
	private String desc;
	public DocSlotBean(String name, String time, String desc) {
		super();
		this.name = name;
		Time = time;
		this.desc = desc;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTime() {
		return Time;
	}
	public void setTime(String time) {
		Time = time;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	

}
