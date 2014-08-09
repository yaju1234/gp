package com.gpcare.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.gpcare.constants.Constants;
import com.gpcare.model.AdminListener;
import com.gpcare.model.SignInListener;
import com.gpcare.model.SignInView;
import com.gpcare.model.SignUpListener;
import com.gpcare.model.SignUpView;
import com.gpcare.model.UserEditProfile;
import com.gpcare.model.UserPrescription.UserPrescritionBackListener;
import com.gpcare.model.UserProfile;
import com.gpcare.model.UserProfileListener;
import com.gpcare.model.doctor.DoctorHome;
import com.gpcare.model.doctor.DoctorProfileListener;
import com.gpcare.model.doctor.DoctorSignInView;
import com.gpcare.screen.BaseScreen;
import com.gpcare.screen.R;
import com.gpcare.screen.R.id;

public class HomeFragment extends Fragment implements SignUpListener,SignInListener, OnClickListener,UserProfileListener,UserEditProfile.UserProfileBackListener,UserPrescritionBackListener,DoctorProfileListener{
	public BaseScreen base;
	private Button btn_login,btn_register,btn_doctor_login;
	private LinearLayout ll_home;
	private RelativeLayout rl_home;
	private Button btn_admin_login = null;
	private AdminListener listenr;
	private boolean flag = false;
	
	String imagepath,fname,lname,address,dob,email,contact,conf_contact;
	
	public HomeFragment(BaseScreen b,AdminListener l, boolean flag){
		listenr = l;
		base = b;
		this.flag = flag;
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment_home, container, false);
		ll_home = (LinearLayout)view.findViewById(R.id.ll_home);
		ll_home.setVisibility(View.GONE);
		
		rl_home = (RelativeLayout)view.findViewById(R.id.rl_home);
		rl_home.setVisibility(View.VISIBLE);
		
		btn_login = (Button)view.findViewById(id.btn_login);
		btn_login.setOnClickListener(this);
		
		btn_register = (Button)view.findViewById(R.id.btn_register);
		btn_register.setOnClickListener(this);
		
		btn_admin_login = (Button)view.findViewById(R.id.btn_admin_login);
		btn_admin_login.setOnClickListener(this);
		
		btn_doctor_login = (Button)view.findViewById(R.id.btn_doctor_login);
		btn_doctor_login.setOnClickListener(this);
		
		if(flag){
			gotosignIn();
		}
		return view;
	}

	private void gotosignIn() {
		this.imagepath = base.app.getUserinfo().image;
		this.fname = base.app.getUserinfo().first_name;
		this.lname = base.app.getUserinfo().last_name;
		this.email = base.app.getUserinfo().email;
		this.dob = base.app.getUserinfo().dob;
		this.contact = base.app.getUserinfo().contact;
		this.conf_contact = base.app.getUserinfo().emrg_contact;
		this.address = base.app.getUserinfo().address;
		
		ll_home.removeAllViews();
		ll_home.setVisibility(View.VISIBLE);
		rl_home.setVisibility(View.GONE);
		ll_home.addView(new UserProfile(base,this,this, imagepath, fname, lname,email, address, dob, contact, conf_contact).mView);
	}

	@Override
	public void onUserSignIn(String fname, String lname,String email,
			String image, String dob, String address,String contact, String emgcontact) {
		
		this.imagepath = image;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.dob = dob;
		this.contact = contact;
		this.conf_contact = emgcontact;
		this.address = address;
		
		ll_home.removeAllViews();
		ll_home.setVisibility(View.VISIBLE);
		rl_home.setVisibility(View.GONE);
		ll_home.addView(new UserProfile(base,this,this, image, fname, lname,email, address, dob, contact, emgcontact).mView);
	}

	@Override
	public void OnSignUp() {
		ll_home.removeAllViews();
		ll_home.setVisibility(View.VISIBLE);
		rl_home.setVisibility(View.GONE);
		ll_home.addView(new SignUpView(base,this,this).mView);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_login:
			ll_home.removeAllViews();
			ll_home.setVisibility(View.VISIBLE);
			rl_home.setVisibility(View.GONE);
			ll_home.addView(new SignInView(base,this,this,false,Constants.USER).mView);
			break;

		case R.id.btn_register:
			ll_home.removeAllViews();
			ll_home.setVisibility(View.VISIBLE);
			rl_home.setVisibility(View.GONE);
			ll_home.addView(new SignUpView(base,this,this).mView);
			break;
			
		case R.id.btn_admin_login:
			listenr.onAdminLogin();
			break;
			
		case R.id.btn_doctor_login:
			ll_home.removeAllViews();
			ll_home.setVisibility(View.VISIBLE);
			rl_home.setVisibility(View.GONE);
			ll_home.addView(new DoctorSignInView(base).mView);
			break;
		}
	}

	@Override
	public void CallBackFromSignUp() {
		ll_home.removeAllViews();
		ll_home.setVisibility(View.VISIBLE);
		rl_home.setVisibility(View.GONE);
		ll_home.addView(new SignInView(base,this,this,false,Constants.USER).mView);
	}

	@Override
	public void onLodaProfile() {
		// TODO Auto-generated method stub
		
	}

	/*@Override
	public void onEditUserProfile(BaseScreen b, String imagepath, String fname,
			String lname, String email, String address, String dob,
			String contact, String conf_contact) {
		ll_home.removeAllViews();
		ll_home.setVisibility(View.VISIBLE);
		rl_home.setVisibility(View.GONE);
		ll_home.addView(new UserEditProfile(base, imagepath, fname, lname, email, address, dob, contact, conf_contact).mView);
	}*/

	@Override
	public void onAdminLogin(String fname, String lname, String email) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDoneClick(BaseScreen b, UserProfileListener listener,
			String imagepath, String fname, String lname, String email,
			String address, String dob, String contact, String conf_contact) {
		
		this.imagepath = imagepath;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.dob = dob;
		this.contact = contact;
		this.conf_contact = conf_contact;
		this.address = address;
		
		ll_home.removeAllViews();
		ll_home.setVisibility(View.VISIBLE);
		rl_home.setVisibility(View.GONE);
		ll_home.addView(new UserProfile(base,this,this, imagepath, fname, lname,email, address, dob, contact, conf_contact).mView);
	}

	/*@Override
	public void onEditUserProfile(BaseScreen b, String imagepath, String fname,
			String lname, String email, String address, String dob,
			String contact, String conf_contact) {
		
	}*/

	public void onPrescritionDoneClick() {
		ll_home.removeAllViews();
		ll_home.setVisibility(View.VISIBLE);
		rl_home.setVisibility(View.GONE);
		ll_home.addView(new UserProfile(base,this,this, imagepath, fname, lname,email, address, dob, contact, conf_contact).mView);
	}

	@Override
	public void onEditUserProfile(BaseScreen b, String imagepath, String fname,
			String lname, String email, String address, String dob,
			String contact, String conf_contact) {
		
	}

	@Override
	public void onCallBackToDoctorProfile() {
		ll_home.removeAllViews();
		ll_home.setVisibility(View.VISIBLE);
		rl_home.setVisibility(View.GONE);
		ll_home.addView(new DoctorHome(base,imagepath, fname, lname,email, address, dob, contact, conf_contact).mView);
	}

	@Override
	public void onCallToDoctorProfile(String imagepath, String fname,
			String lname, String email, String address, String dob,
			String contact, String conf_contact) {
		ll_home.removeAllViews();
		ll_home.setVisibility(View.VISIBLE);
		rl_home.setVisibility(View.GONE);
		ll_home.addView(new DoctorHome(base,imagepath, fname, lname,email, address, dob, contact, conf_contact).mView);
	}
}
