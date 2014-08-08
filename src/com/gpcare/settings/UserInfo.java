package com.gpcare.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.gpcare.constants.Constants;

public class UserInfo {
	
	public String user_id = null;
	public String gender = null;
	public String email = null;	
	public String first_name = null;
	public String last_name = null;
	public String address = null;
	public String profile_pic = null;
	public String dob = null;
	
	public SharedPreferences preference = null;
	
	public UserInfo(Context ctx){
		
		preference = ctx.getSharedPreferences(Constants.values.USRINFO.name(), Context.MODE_PRIVATE);
		user_id = preference.getString(Constants.values.USERID.name(), null);
		gender = preference.getString(Constants.values.GENDER.name(), null);
		first_name = preference.getString(Constants.values.FIRSTNAME.name(), null);
		last_name = preference.getString(Constants.values.LASTNAME.name(), null);
		email = preference.getString(Constants.values.EMAIL.name(), null);		
		address = preference.getString(Constants.values.ADDRESS.name(), null);
		profile_pic = preference.getString(Constants.values.PROFILEPIC.name(), null);
		dob = preference.getString(Constants.values.DOB.name(), null);
		
	}

	public void SetUserInfo(String user_id,String first_name, String last_name,String email,
			String address, String profile_pic, String dob) {
		this.gender = gender;
		this.email = email;
		this.user_id = user_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.address = address;
		this.profile_pic = profile_pic;
		this.dob = dob;
		
		Editor edit = preference.edit();
		edit.putString(Constants.values.USERID.name(), user_id);
		edit.putString(Constants.values.GENDER.name(), gender);
		edit.putString(Constants.values.FIRSTNAME.name(), first_name);
		edit.putString(Constants.values.LASTNAME.name(), last_name);
		edit.putString(Constants.values.EMAIL.name(), email);		
		edit.putString(Constants.values.ADDRESS.name(), address);
		edit.putString(Constants.values.PROFILEPIC.name(), profile_pic);
		dob = preference.getString(Constants.values.DOB.name(), dob);
		edit.commit();
	}

	public void setGender(String gender) {
		this.gender = gender;
		Editor edit = preference.edit();
		edit.putString(Constants.values.GENDER.name(), gender);		
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

	public void setAddress(String address) {
		this.address = address;
		Editor edit = preference.edit();
		edit.putString(Constants.values.ADDRESS.name(), address);		
		edit.commit();
	}

	public void setProfile_pic(String profile_pic) {
		this.profile_pic = profile_pic;
		Editor edit = preference.edit();
		edit.putString(Constants.values.PROFILEPIC.name(), profile_pic);		
		edit.commit();
	}


}
