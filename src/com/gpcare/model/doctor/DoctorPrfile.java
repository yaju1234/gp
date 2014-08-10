package com.gpcare.model.doctor;

import android.view.View;
import android.widget.TextView;

import com.gpcare.screen.BaseScreen;
import com.gpcare.screen.R;

public class DoctorPrfile {

	public BaseScreen base;
	public View mView;
	String imagepath,fname,lname,email,degree,specilization;
	private TextView tv_profile_email,tv_degree,tv_specilization;
	
	public DoctorPrfile(BaseScreen b, String imagepath, String fname, String lname, String email, String degree, String specilization){
		this.base = b;
		mView = View.inflate(base, R.layout.doctor_profile, null);
		
		this.imagepath = imagepath;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.degree = degree;
		this.specilization = specilization;
		
		
		tv_profile_email = (TextView)mView.findViewById(R.id.tv_profile_email);
		tv_degree = (TextView)mView.findViewById(R.id.tv_degree);
		tv_specilization = (TextView)mView.findViewById(R.id.tv_specilization);
		
		tv_profile_email.setText(email);
		tv_degree.setText(degree);
		tv_specilization.setText(specilization);
	}
}
