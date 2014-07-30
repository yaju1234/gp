package com.gpcare.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.gpcare.constants.Constants;

public class LoginInfo {

	public String UserName = null;
	public String Password = null;
	
	public SharedPreferences preference;
	
	public LoginInfo(Context context){
		preference = context.getSharedPreferences(Constants.values.USRINFO.name(), context.MODE_PRIVATE);
		UserName = preference.getString(Constants.values.USERNAME.name(), null);
		Password = preference.getString(Constants.values.PASSWORD.name(), null);
	}
	
	public void setLoginInfo(String username,String password){
		this.UserName = username;
		this.Password = password;
		Editor edit = preference.edit();
		edit.putString(Constants.values.USERNAME.name(), username);
		edit.putString(Constants.values.PASSWORD.name(), password);
		edit.commit();
	}
}
