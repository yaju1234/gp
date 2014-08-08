package com.gpcare.bean;

public class DoctorBean {

	String doctor_name;
	String doctor_specilization;
	String doctor_degree;
	
	public DoctorBean(String doctor_name, String doctor_specilization,
			String doctor_degree) {
		super();
		this.doctor_name = doctor_name;
		this.doctor_specilization = doctor_specilization;
		this.doctor_degree = doctor_degree;
	}

	public String getDoctor_name() {
		return doctor_name;
	}

	public void setDoctor_name(String doctor_name) {
		this.doctor_name = doctor_name;
	}

	public String getDoctor_specilization() {
		return doctor_specilization;
	}

	public void setDoctor_specilization(String doctor_specilization) {
		this.doctor_specilization = doctor_specilization;
	}

	public String getDoctor_degree() {
		return doctor_degree;
	}

	public void setDoctor_degree(String doctor_degree) {
		this.doctor_degree = doctor_degree;
	}
	
	
}
