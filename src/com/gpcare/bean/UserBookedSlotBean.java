package com.gpcare.bean;

public class UserBookedSlotBean {
	
	private String appo_no;
	private String time,date,doctor;
	private String problem;
	
	public UserBookedSlotBean(String appo_no, String time, String date,
			String doctor, String problem) {
		super();
		this.appo_no = appo_no;
		this.time = time;
		this.date = date;
		this.doctor = doctor;
		this.problem = problem;
	}

	public String getAppo_no() {
		return appo_no;
	}

	public void setAppo_no(String appo_no) {
		this.appo_no = appo_no;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDoctor() {
		return doctor;
	}

	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}

	public String getProblem() {
		return problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}
}
