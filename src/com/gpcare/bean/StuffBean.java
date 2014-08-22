package com.gpcare.bean;

public class StuffBean {

	String stuff_id;
	String stuff_name;
	String stuff_specilization;
	String stuff_degree;
	public StuffBean(String stuff_id, String stuff_name,
			String stuff_specilization, String stuff_degree) {
		super();
		this.stuff_id = stuff_id;
		this.stuff_name = stuff_name;
		this.stuff_specilization = stuff_specilization;
		this.stuff_degree = stuff_degree;
	}
	public String getStuff_id() {
		return stuff_id;
	}
	public void setStuff_id(String stuff_id) {
		this.stuff_id = stuff_id;
	}
	public String getStuff_name() {
		return stuff_name;
	}
	public void setStuff_name(String stuff_name) {
		this.stuff_name = stuff_name;
	}
	public String getStuff_specilization() {
		return stuff_specilization;
	}
	public void setStuff_specilization(String stuff_specilization) {
		this.stuff_specilization = stuff_specilization;
	}
	public String getStuff_degree() {
		return stuff_degree;
	}
	public void setStuff_degree(String stuff_degree) {
		this.stuff_degree = stuff_degree;
	}
	
	
}
