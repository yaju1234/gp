package com.gpcare.model.doctor;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gpcare.screen.BaseScreen;
import com.gpcare.screen.R;
import com.gpcare.settings.ImageLoader;

public class DoctorHome implements OnClickListener,DoctorListener{

	public interface DocLogoutListener {
		public void onDocLogout();
	}
	
	public BaseScreen base;
	public View mView;
	private ImageView iv_profile_image,iv_settings;
	
	public ImageLoader imageloader;
	String imagepath,fname,lname,degree,email,contact,specilization;
	public RelativeLayout rl_popup;
	private TextView tv_leave_req,tv_logout,tv_user_name,tv_appointment;
	private boolean isVisiable = false;
	private LinearLayout rl_cointainer;
	private DocLogoutListener logoutlistener;
	
	public DoctorHome(BaseScreen b,String imagepath, String fname, String lname, String email, String degree, String specilization){
		this.base = b;
		logoutlistener = (DocLogoutListener)base;
		
		this.imagepath = imagepath;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.degree = degree;
		this.specilization = specilization;
		
		mView = View.inflate(base, R.layout.home_doctor, null);
		iv_profile_image = (ImageView)mView.findViewById(R.id.iv_profile_image);
		
		rl_popup = (RelativeLayout)mView.findViewById(R.id.rl_popup);
		rl_popup.setVisibility(View.GONE);
		
		
		tv_leave_req = (TextView)mView.findViewById(R.id.tv_leave_req);
		tv_leave_req.setOnClickListener(this);
		
		tv_logout = (TextView)mView.findViewById(R.id.tv_logout);
		tv_logout.setOnClickListener(this);
		
		iv_settings = (ImageView)mView.findViewById(R.id.iv_settings);
		iv_settings.setOnClickListener(this);
		
		rl_cointainer = (LinearLayout)mView.findViewById(R.id.rl_cointainer);
		
		if(!imagepath.equalsIgnoreCase("")){
			imageloader.DisplayImage("", iv_profile_image);
		}
		
		tv_user_name = (TextView)mView.findViewById(R.id.tv_user_name);
		tv_user_name.setText(fname + " "+lname);
		tv_appointment = (TextView)mView.findViewById(R.id.tv_appointment);
		tv_appointment.setOnClickListener(this);
		loadProfileScreen();
	}
	
	private void loadProfileScreen() {
		rl_cointainer.removeAllViews();
		rl_cointainer.addView(new DoctorPrfile(base,imagepath,fname, lname,email,degree,specilization).mView);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_appointment:
			rl_cointainer.removeAllViews();
			rl_cointainer.addView(new DoctorSlotBooking(base).view);
			break;
		case R.id.tv_leave_req:
			rl_popup.setVisibility(View.GONE);
			isVisiable = false;
			rl_cointainer.removeAllViews();
			rl_cointainer.addView(new DoctorLeaveRequest(base,this).mView);
			break;
		case R.id.tv_logout:
			base.app.getDoctorinfo().setSession(false);
			logoutlistener.onDocLogout();
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
