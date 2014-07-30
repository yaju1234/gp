package com.gpcare.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.gpcare.constants.Constants;

public class UserInfo {

	public String gender = null;
	public String email = null;
	public String user_id = null;
	public String prifile_id = null;
	public String first_name = null;
	public String last_name = null;
	public String address = null;
	public String profile_pic = null;
	public String profile_desc = null;
	public String university = null;
	
	
	public SharedPreferences preference = null;
	
	public UserInfo(Context ctx){
		
		System.out.println("!--please enter here");
		
		preference = ctx.getSharedPreferences(Constants.values.USRINFO.name(), Context.MODE_PRIVATE);
		email = preference.getString(Constants.values.INDIVIDUAL_EMAIL.name(), null);
		gender = preference.getString(Constants.values.GENDER.name(), null);
		user_id = preference.getString(Constants.values.INDI_USERID.name(), null);
		prifile_id = preference.getString(Constants.values.INDI_PROFILEID.name(), null);
		first_name = preference.getString(Constants.values.INDI_FIRSTNAME.name(), null);
		last_name = preference.getString(Constants.values.INDI_LASTNAME.name(), null);
		address = preference.getString(Constants.values.INDI_ADDRESS.name(), null);
		profile_pic = preference.getString(Constants.values.INDI_PROFILEPIC.name(), null);
		profile_desc = preference.getString(Constants.values.INDI_PROFILEDESC.name(), null);
		university = preference.getString(Constants.values.INDI_UNIVERSITY.name(), null);
	}

	public void SetUserInfo(String gender, String email, String user_id,
			String prifile_id, String first_name, String last_name,
			String address, String profile_pic,String profile_desc, String university) {
		this.gender = gender;
		this.email = email;
		this.user_id = user_id;
		this.prifile_id = prifile_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.address = address;
		this.profile_pic = profile_pic;
		this.profile_desc = profile_desc;
		this.university = university;
		
		Editor edit = preference.edit();
		edit.putString(Constants.values.GENDER.name(), email);
		edit.putString(Constants.values.INDIVIDUAL_EMAIL.name(), email);
		edit.putString(Constants.values.INDI_USERID.name(), user_id);
		edit.putString(Constants.values.INDI_PROFILEID.name(), prifile_id);
		edit.putString(Constants.values.INDI_FIRSTNAME.name(), first_name);
		edit.putString(Constants.values.INDI_LASTNAME.name(), last_name);
		edit.putString(Constants.values.INDI_ADDRESS.name(), address);
		edit.putString(Constants.values.INDI_PROFILEPIC.name(), profile_pic);
		edit.putString(Constants.values.INDI_PROFILEDESC.name(), profile_desc);
		edit.putString(Constants.values.INDI_UNIVERSITY.name(), university);
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
		edit.putString(Constants.values.INDIVIDUAL_EMAIL.name(), email);		
		edit.commit();
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
		Editor edit = preference.edit();
		edit.putString(Constants.values.INDI_USERID.name(), user_id);		
		edit.commit();
	}

	public void setPrifile_id(String prifile_id) {
		this.prifile_id = prifile_id;
		Editor edit = preference.edit();
		edit.putString(Constants.values.INDI_PROFILEID.name(), prifile_id);		
		edit.commit();
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
		Editor edit = preference.edit();
		edit.putString(Constants.values.INDI_FIRSTNAME.name(), first_name);		
		edit.commit();
	}
	
	public void setLast_name(String last_name) {
		this.last_name = last_name;
		Editor edit = preference.edit();
		edit.putString(Constants.values.INDI_LASTNAME.name(), last_name);		
		edit.commit();
	}

	public void setAddress(String address) {
		this.address = address;
		Editor edit = preference.edit();
		edit.putString(Constants.values.INDI_ADDRESS.name(), address);		
		edit.commit();
	}

	public void setProfile_pic(String profile_pic) {
		this.profile_pic = profile_pic;
		Editor edit = preference.edit();
		edit.putString(Constants.values.INDI_PROFILEPIC.name(), profile_pic);		
		edit.commit();
	}

	public void setUniversity(String university) {
		this.university = university;
		Editor edit = preference.edit();
		edit.putString(Constants.values.INDI_UNIVERSITY.name(), university);		
		edit.commit();
	}
	
	public void setProfile_desc(String profile_desc) {
		this.profile_desc = profile_desc;
		Editor edit = preference.edit();
		edit.putString(Constants.values.INDI_PROFILEDESC.name(), university);		
		edit.commit();
	}

	
}
