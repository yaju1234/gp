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
	public String image = "";
	public String contact = null;
	public String emrg_contact = null;
	public boolean session = false;
	
	public SharedPreferences preference = null;
	
	public UserInfo(Context ctx){
		
		preference = ctx.getSharedPreferences(Constants.values.USRINFO.name(), Context.MODE_PRIVATE);
		user_id = preference.getString(Constants.values.USERID.name(), null);
		image = preference.getString(Constants.values.IMAGE.name(), "");
		first_name = preference.getString(Constants.values.FIRSTNAME.name(), null);
		last_name = preference.getString(Constants.values.LASTNAME.name(), null);
		email = preference.getString(Constants.values.EMAIL.name(), null);		
		address = preference.getString(Constants.values.ADDRESS.name(), null);
		profile_pic = preference.getString(Constants.values.PROFILEPIC.name(), null);
		dob = preference.getString(Constants.values.DOB.name(), null);
		contact = preference.getString(Constants.values.CONTACT.name(), null);
		emrg_contact = preference.getString(Constants.values.EMRG_CONTACT.name(), null);
		session =  preference.getBoolean(Constants.values.SESSION.name(), false);
		
	}

	public void SetUserInfo(String user_id,String first_name, String last_name,String email,String image,
			String address, String profile_pic, String dob,String contact,String emrg_contact,boolean session) {
		this.image = image;
		this.email = email;
		this.user_id = user_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.address = address;
		this.profile_pic = profile_pic;
		this.dob = dob;
		this.contact = contact;
		this.emrg_contact = emrg_contact;
		this.session = session;
		
		Editor edit = preference.edit();
		edit.putString(Constants.values.USERID.name(), user_id);
		edit.putString(Constants.values.IMAGE.name(), gender);
		edit.putString(Constants.values.FIRSTNAME.name(), first_name);
		edit.putString(Constants.values.LASTNAME.name(), last_name);
		edit.putString(Constants.values.EMAIL.name(), email);		
		edit.putString(Constants.values.ADDRESS.name(), address);
		edit.putString(Constants.values.PROFILEPIC.name(), profile_pic);
		edit.putString(Constants.values.DOB.name(), dob);
		edit.putString(Constants.values.CONTACT.name(), contact);
		edit.putString(Constants.values.EMRG_CONTACT.name(), emrg_contact);
		edit.putBoolean(Constants.values.SESSION.name(), session);
		edit.commit();
	}

	public void setGender(String gender) {
		this.gender = gender;
		Editor edit = preference.edit();
		edit.putString(Constants.values.IMAGE.name(), gender);		
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
	
	public void setSession(Boolean flag) {
		this.session = flag;
		Editor edit = preference.edit();
		edit.putBoolean(Constants.values.SESSION.name(), session);		
		edit.commit();
	}
	
	public void setContact(String contact) {
		this.contact = contact;
		Editor edit = preference.edit();
		edit.putString(Constants.values.CONTACT.name(), contact);		
		edit.commit();
	}

	public void setEmrgContact(String emrg_contact) {
		this.emrg_contact = emrg_contact;
		Editor edit = preference.edit();
		edit.putString(Constants.values.EMRG_CONTACT.name(), emrg_contact);		
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
	
	public void setSession(boolean session) {
		this.session = session;
		Editor edit = preference.edit();
		edit.putBoolean(Constants.values.SESSION.name(), session);		
		edit.commit();
	}
}
