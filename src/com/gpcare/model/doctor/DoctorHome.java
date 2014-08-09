package com.gpcare.model.doctor;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gpcare.screen.BaseScreen;
import com.gpcare.screen.R;
import com.gpcare.settings.ImageLoader;

public class DoctorHome implements OnClickListener,DoctorListener{

	public interface LogoutListener {
		public void onLogout();
	}
	
	public BaseScreen base;
	public View mView;
	private ImageView iv_profile_image,iv_settings;
	
	public ImageLoader imageloader;
	String imagepath,fname,lname,address,dob,email,contact,conf_contact;
	public RelativeLayout rl_popup,rl_cointainer;
	private TextView tv_edit_profile,tv_appointment,tv_repeat_prescription,tv_logout;
	private boolean isVisiable = false;
	private LogoutListener logoutlistener;
	private DoctorProfileListener listener;
	
	public DoctorHome(BaseScreen b,String imagepath2, String fname2, String lname2, String email2, String address2, String dob2, String contact2, String conf_contact2){
		this.base = b;
		
		logoutlistener = (LogoutListener)base;
		mView = View.inflate(base, R.layout.home_doctor, null);
		iv_profile_image = (ImageView)mView.findViewById(R.id.iv_profile_image);
		
		rl_popup = (RelativeLayout)mView.findViewById(R.id.rl_popup);
		rl_popup.setVisibility(View.GONE);
		
		tv_edit_profile = (TextView)mView.findViewById(R.id.tv_edit_profile);
		tv_edit_profile.setOnClickListener(this);
		
		tv_appointment = (TextView)mView.findViewById(R.id.tv_appointment);
		tv_appointment.setOnClickListener(this);
		
		tv_repeat_prescription = (TextView)mView.findViewById(R.id.tv_repeat_prescription);
		tv_repeat_prescription.setOnClickListener(this);
		
		tv_logout = (TextView)mView.findViewById(R.id.tv_logout);
		tv_logout.setOnClickListener(this);
		
		iv_settings = (ImageView)mView.findViewById(R.id.iv_settings);
		iv_settings.setOnClickListener(this);
		
		rl_cointainer = (RelativeLayout)mView.findViewById(R.id.rl_cointainer);
		
		if(!imagepath.equalsIgnoreCase("")){
			imageloader.DisplayImage("", iv_profile_image);
		}
		
		loadProfileScreen();
	}
	
	private void loadProfileScreen() {
		rl_cointainer.removeAllViews();
		rl_cointainer.addView(new DoctorPrfile(base,fname, lname,email, address, dob, contact, conf_contact).mView);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_edit_profile:
			rl_popup.setVisibility(View.GONE);
			isVisiable = false;
			rl_cointainer.removeAllViews();
			rl_cointainer.addView(new DoctorEditProfile(base,this,imagepath, fname, lname, email, address, dob, contact, conf_contact).mView);			
			break;
		case R.id.tv_appointment:
			
			break;
		case R.id.tv_leave_req:
			rl_popup.setVisibility(View.GONE);
			isVisiable = false;
			rl_cointainer.removeAllViews();
			rl_cointainer.addView(new DoctorLeaveRequest(base,this).mView);
			break;
		case R.id.tv_logout:
			logoutlistener.onLogout();
			break;
		case R.id.iv_settings:
			if(!isVisiable){
				isVisiable = true;
				rl_popup.setVisibility(View.VISIBLE);
			}else{
				isVisiable = false;
				rl_popup.setVisibility(View.GONE);
			}
			break;
		}
	}

	@Override
	public void backDoctorHome() {
		loadProfileScreen();
	}
}
