package com.gpcare.settings;

import android.app.Application;

public class Appsettings extends Application{

	
	private UserInfo userinfo;	
	private AdminInfo admininfo;
	public DoctorInfo doctorinfo;
	public boolean init = false;

	public UserInfo getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(UserInfo userinfo) {
		this.userinfo = userinfo;
	}

	public AdminInfo getAdmininfo() {
		return admininfo;
	}

	public void setAdmininfo(AdminInfo admininfo) {
		this.admininfo = admininfo;
	}

	public DoctorInfo getDoctorinfo() {
		return doctorinfo;
	}

	public void setDoctorinfo(DoctorInfo doctorinfo) {
		this.doctorinfo = doctorinfo;
	}
}
