package com.gpcare.bean;

public class QueryBean {

	public String name,email;
	public String message;
	public String id;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public QueryBean(String name, String email, String message, String id) {
		super();
		this.name = name;
		this.email = email;
		this.message = message;
		this.id = id;
	}
}
