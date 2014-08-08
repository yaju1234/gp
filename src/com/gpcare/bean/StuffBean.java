package com.gpcare.bean;

public class StuffBean {

	String stuff_name;
	String stuff_specilization;
	String stuff_degree;
	
	public StuffBean(String stuff_name, String stuff_specilization,
			String stuff_degree) {
		super();
		this.stuff_name = stuff_name;
		this.stuff_specilization = stuff_specilization;
		this.stuff_degree = stuff_degree;
	}

	public String getstuff_name() {
		return stuff_name;
	}

	public void setstuff_name(String stuff_name) {
		this.stuff_name = stuff_name;
	}

	public String getstuff_specilization() {
		return stuff_specilization;
	}

	public void setstuff_specilization(String stuff_specilization) {
		this.stuff_specilization = stuff_specilization;
	}

	public String getstuff_degree() {
		return stuff_degree;
	}

	public void setstuff_degree(String stuff_degree) {
		this.stuff_degree = stuff_degree;
	}
	
	
}
