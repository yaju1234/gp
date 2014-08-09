package com.gpcare.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.gpcare.constants.Constants;

public class AdminInfo {
	
	public String user_id = null;
	public String email = null;	
	public String first_name = null;
	public String last_name = null;
	public String profile_pic = null;
	public boolean  session = false;
	
	public SharedPreferences preference = null;
	
	public AdminInfo(Context ctx){
		
		preference = ctx.getSharedPreferences(Constants.values.ADMININFO.name(), Context.MODE_PRIVATE);
		user_id = preference.getString(Constants.values.USERID.name(), null);
		first_name = preference.getString(Constants.values.FIRSTNAME.name(), null);
		last_name = preference.getString(Constants.values.LASTNAME.name(), null);
		email = preference.getString(Constants.values.EMAIL.name(), null);		
		profile_pic = preference.getString(Constants.values.PROFILEPIC.name(), null);
		session = preference.getBoolean(Constants.values.SESSION.name(), session);
		
	}

	public void SetAdminInfo(String user_id,String email,String first_name, String last_name, String profile_pic) {
		this.email = email;
		this.user_id = user_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.profile_pic = profile_pic;
		
		Editor edit = preference.edit();
		edit.putString(Constants.values.USERID.name(), user_id);
		edit.putString(Constants.values.FIRSTNAME.name(), first_name);
		edit.putString(Constants.values.LASTNAME.name(), last_name);
		edit.putString(Constants.values.EMAIL.name(), email);		
		edit.putString(Constants.values.PROFILEPIC.name(), profile_pic);
		edit.commit();
	}

	
	public void setEmail(String email) {
		this.email = email;
		Editor edit = preference.edit();
		edit.putString(Constants.values.EMAIL.name(), email);		
		edit.commit();
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
		Editor edit = preference.edit();
		edit.putString(Constants.values.USERID.name(), user_id);		
		edit.commit();
	}

	
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
		Editor edit = preference.edit();
		edit.putString(Constants.values.FIRSTNAME.name(), first_name);		
		edit.commit();
	}
	
	public void setLast_name(String last_name) {
		this.last_name = last_name;
		Editor edit = preference.edit();
		edit.putString(Constants.values.LASTNAME.name(), last_name);		
		edit.commit();
	}


	public void setProfile_pic(String profile_pic) {
		this.profile_pic = profile_pic;
		Editor edit = preference.edit();
		edit.putString(Constants.values.PROFILEPIC.name(), profile_pic);		
		edit.commit();
	}
	
	public void setSession(boolean session) {
		this.session = session;
		Editor edit = preference.edit();
		edit.putBoolean(Constants.values.SESSION.name(), session);		
		edit.commit();
	}
}
