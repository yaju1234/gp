package com.gpcare.model.doctor;

import android.view.View;
import android.widget.TextView;

import com.gpcare.screen.BaseScreen;
import com.gpcare.screen.R;

public class DoctorPrfile {

	public BaseScreen base;
	public View mView;
	String imagepath,fname,lname,address,dob,email,contact,conf_contact;
	private TextView tv_user_name,tv_profile_location,tv_profile_email,tv_dob,tv_contact,tv_emergency_contact;
	
	public DoctorPrfile(BaseScreen b, String imagepath2, String fname2, String lname2, String email2, String address2, String dob2, String contact2){
		this.base = b;
		mView = View.inflate(base, R.layout.doctor_profile, null);
		
		this.imagepath = imagepath;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.dob = dob;
		this.contact = contact;
		this.conf_contact = conf_contact;
		this.address = address;
		
		
		tv_user_name = (TextView)mView.findViewById(R.id.tv_user_name);
		tv_profile_location = (TextView)mView.findViewById(R.id.tv_profile_location);
		tv_profile_email = (TextView)mView.findViewById(R.id.tv_profile_email);
		tv_dob = (TextView)mView.findViewById(R.id.tv_dob);
		tv_contact = (TextView)mView.findViewById(R.id.tv_contact);
		tv_emergency_contact = (TextView)mView.findViewById(R.id.tv_emergency_contact);
		
		tv_profile_location.setText(address);
		tv_profile_email.setText(email);
		tv_dob.setText(dob);
		tv_contact.setText(contact);
		tv_emergency_contact.setText(conf_contact);
		tv_user_name.setText(fname + " "+lname);
		tv_profile_email.setText(email);
	}
}
