package com.gpcare.model;

public interface SignInListener {	
	public void onUserSignIn(String fname, String lname,String email,
			String image, String dob, String address,String contact, String emgcontact);
	public void onLodaProfile();
	public void onAdminLogin(String fname, String lname, String email);
	public void onCallToDoctorProfile(String imagepath,String fname, String lname, String email, String address,String dob, String contact, String conf_contact);
}
