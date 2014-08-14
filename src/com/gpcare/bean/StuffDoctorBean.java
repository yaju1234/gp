package com.gpcare.bean;

public class StuffDoctorBean {
	
	private String id;
	private String name;
	private String specialization;
	private String degree;
	public StuffDoctorBean(String id,String name, String specialization, String degree) {
		super();
		this.id = id;
		this.name = name;
		this.specialization = specialization;
		this.degree = degree;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
}
