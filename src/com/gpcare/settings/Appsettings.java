package com.gpcare.settings;

import android.app.Application;

public class Appsettings extends Application{

	private LoginInfo loginfo;
	private UserInfo userinfo;
	
	public boolean init = false;

	public LoginInfo getLoginfo() {
		return loginfo;
	}

	public void setLoginfo(LoginInfo loginfo) {
		this.loginfo = loginfo;
	}

	public UserInfo getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(UserInfo userinfo) {
		this.userinfo = userinfo;
	}
}
