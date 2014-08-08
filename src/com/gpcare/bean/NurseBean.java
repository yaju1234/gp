package com.gpcare.bean;

public class NurseBean {

	String nurse_name;
	String nurse_specilization;
	String nurse_degree;
	
	public NurseBean(String nurse_name, String nurse_specilization,
			String nurse_degree) {
		super();
		this.nurse_name = nurse_name;
		this.nurse_specilization = nurse_specilization;
		this.nurse_degree = nurse_degree;
	}

	public String getnurse_name() {
		return nurse_name;
	}

	public void setnurse_name(String nurse_name) {
		this.nurse_name = nurse_name;
	}

	public String getnurse_specilization() {
		return nurse_specilization;
	}

	public void setnurse_specilization(String nurse_specilization) {
		this.nurse_specilization = nurse_specilization;
	}

	public String getnurse_degree() {
		return nurse_degree;
	}

	public void setnurse_degree(String nurse_degree) {
		this.nurse_degree = nurse_degree;
	}
	
	
}
