package com.gpcare.settings;

import android.app.Application;

public class Appsettings extends Application{

	
	private UserInfo userinfo;	
	private AdminInfo admininfo;
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
}
