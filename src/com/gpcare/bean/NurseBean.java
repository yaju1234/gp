package com.gpcare.bean;

public class NurseBean {

	String nurse_id;
	String nurse_name;
	String nurse_specilization;
	String nurse_degree;
	public NurseBean(String nurse_id, String nurse_name,
			String nurse_specilization, String nurse_degree) {
		super();
		this.nurse_id = nurse_id;
		this.nurse_name = nurse_name;
		this.nurse_specilization = nurse_specilization;
		this.nurse_degree = nurse_degree;
	}
	public String getNurse_id() {
		return nurse_id;
	}
	public void setNurse_id(String nurse_id) {
		this.nurse_id = nurse_id;
	}
	public String getNurse_name() {
		return nurse_name;
	}
	public void setNurse_name(String nurse_name) {
		this.nurse_name = nurse_name;
	}
	public String getNurse_specilization() {
		return nurse_specilization;
	}
	public void setNurse_specilization(String nurse_specilization) {
		this.nurse_specilization = nurse_specilization;
	}
	public String getNurse_degree() {
		return nurse_degree;
	}
	public void setNurse_degree(String nurse_degree) {
		this.nurse_degree = nurse_degree;
	}
	
	
}
