package com.gpcare.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.gpcare.constants.Constants;
import com.gpcare.model.SignInListener;
import com.gpcare.model.SignInView;
import com.gpcare.model.SignUpListener;
import com.gpcare.model.SignUpView;
import com.gpcare.screen.BaseScreen;
import com.gpcare.screen.R;


@SuppressLint("ValidFragment")
public class AppointmentFragment extends Fragment implements OnClickListener, SignInListener,SignUpListener{
	public BaseScreen base;
	public LinearLayout ll_appointment = null;
	
	
	@SuppressLint("ValidFragment") 
	public AppointmentFragment(BaseScreen b){
		base = b;
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment_appoinment, container, false);
		ll_appointment = (LinearLayout)view.findViewById(R.id.ll_appointment);
		ll_appointment.removeAllViews();
		ll_appointment.addView(new SignInView(base, this, this,true,Constants.USER).mView);
		return view;
	}
	
	public void onClick(View v) {
		
		switch (v.getId()) {
		case R.id.btn_login:
			
			break;
		case R.id.btn_signup:
			
			break;

		}
	}


	public void OnSignUp() {
		ll_appointment.removeAllViews();
		ll_appointment.addView(new SignUpView(base, this, this).mView);
		
	}

	
	public void onSignIn(String userid, String fname, String lname,	String image, String dob) {
		
	}

	
	public void CallBackFromSignUp() {
		ll_appointment.removeAllViews();
		ll_appointment.addView(new SignInView(base, this, this,true,Constants.USER).mView);
	}	
	
	public void onLodaProfile() {
		
		
	}
	public void onUserSignIn(String userid, String fname, String lname,
			String email, String image, String dob, String address,
			String contact, String emgcontact) {
		
		
	}	
	public void onAdminLogin(String fname, String lname, String email) {}

	@Override
	public void onUserSignIn(String fname, String lname, String email,
			String image, String dob, String address, String contact,
			String emgcontact) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCallToDoctorProfile(String imagepath, String fname,
			String lname, String email, String address, String dob,
			String contact, String conf_contact) {
		// TODO Auto-generated method stub
		
	}
}


