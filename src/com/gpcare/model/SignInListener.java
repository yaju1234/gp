package com.gpcare.model;

public interface SignInListener {	
	public void onUserSignIn(String fname, String lname,String email,
			String image, String dob, String address,String contact, String emgcontact);
	public void onLodaProfile();
	public void onAdminLogin(String fname, String lname, String email);
	public void onCallToDoctorProfile(String user_id,String first_name, String last_name,String email,String image,
			String degree, String specilization);
}
