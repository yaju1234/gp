package com.gpcare.model;

import com.gpcare.screen.BaseScreen;

public interface UserProfileListener {
	public void onEditUserProfile(BaseScreen b,String imagepath,String fname,String lname, String email,String address,String dob,String contact,String conf_contact);
}
