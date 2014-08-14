package com.gpcare.screen;

import com.gpcare.fragment.AppointmentFragment;
import com.gpcare.fragment.ContactusFragment;
import com.gpcare.fragment.DoctorFragment;
import com.gpcare.fragment.HomeFragment;
import com.gpcare.fragment.InformationFragment;
import com.gpcare.fragment.StaffFragment;
import com.gpcare.model.AdminListener;
import com.gpcare.model.UserProfile.LogoutListener;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class HomeScreen extends BaseScreen implements AdminListener,LogoutListener{
	private DrawerLayout mDrawerLayout;
	private RelativeLayout list_slidermenu = null;
	private ImageView iv_slider = null,iv_slider_slide = null;
	private String loginType = "";
	private LinearLayout ll_home = null,ll_staffs = null,ll_doctor_availability = null,ll_appointment = null,ll_information_zone = null,ll_contact_us = null;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		list_slidermenu = (RelativeLayout)findViewById(R.id.list_slidermenu);
		iv_slider = (ImageView)findViewById(R.id.iv_slide);
		iv_slider_slide = (ImageView)findViewById(R.id.iv_slider_slide);
		ll_home = (LinearLayout)findViewById(R.id.ll_home);
		ll_staffs = (LinearLayout)findViewById(R.id.ll_staffs);
		ll_doctor_availability = (LinearLayout)findViewById(R.id.ll_doctor_availability);
		ll_appointment = (LinearLayout)findViewById(R.id.ll_appointment);
		ll_information_zone = (LinearLayout)findViewById(R.id.ll_information_zone);
		ll_contact_us = (LinearLayout)findViewById(R.id.ll_contact_us);
		iv_slider.setOnClickListener(this);
		iv_slider_slide.setOnClickListener(this);
		ll_home.setOnClickListener(this);
		ll_staffs.setOnClickListener(this);
		ll_doctor_availability.setOnClickListener(this);
		ll_appointment.setOnClickListener(this);
		ll_information_zone.setOnClickListener(this);
		ll_contact_us.setOnClickListener(this);
		mDrawerLayout.closeDrawers();
		if(app.getAdmininfo().session){
			onAdminLogin();
		}else{
			displayView(0);
		}
		
		System.out.println("!--reach here999"+app.getUserinfo().session);
		
		if(app.getUserinfo().session){
			if(app.getUserinfo().session){
				loginType = "user";
				displayView(0);
			}else{
				loginType = "";
				displayView(0);
			}
		}else if(app.getDoctorinfo().session){
			if(app.getDoctorinfo().session){
				System.out.println("!--reach here5555"+app.getDoctorinfo().session);
				loginType = "doctor";
				displayView(0);
			}else{
				loginType = "";
				displayView(0);
			}
		}
		
		
		System.out.println("##--session"+app.getUserinfo().session);
		System.out.println("##--session"+app.getDoctorinfo().session);
	}	

	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.iv_slide:
			 mDrawerLayout.openDrawer(list_slidermenu);
			break;
		case R.id.iv_slider_slide:
			mDrawerLayout.closeDrawers();
			break;	
		case R.id.ll_home:
			displayView(0);
			break;
		case R.id.ll_staffs:
			displayView(1);
			break;
		case R.id.ll_doctor_availability:
			displayView(2);
			break;
		case R.id.ll_appointment:
			displayView(3);
			break;
		case R.id.ll_information_zone:
			displayView(4);
			break;
		case R.id.ll_contact_us:
			displayView(5);
			break;
		}
	}
	
	private void displayView(int position) {
		
		Fragment fragment = null;
		switch (position) {
		case 0:
			fragment = new HomeFragment(this,this,loginType);
			break;
		case 1:
			fragment = new StaffFragment(this);
			break;
		case 2:
			fragment = new DoctorFragment(this);
			break;
		case 3:
			fragment = new AppointmentFragment(this);
			break;
		case 4:
			fragment = new InformationFragment(this);
			break;
		case 5:
			fragment = new ContactusFragment(this);
			break;
		}

		if (fragment != null) {
			FragmentManager fragmentManager = getSupportFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.frame, fragment).commit();
			mDrawerLayout.closeDrawers();
		} else {
			
			Log.e("MainActivity", "Error in creating fragment");
		}
	}

	public void onAdminLogin() {
		Intent i = new Intent(this,AdminHomeScreen.class);
		startActivity(i);
		finish();
	}

	@Override
	public void onLogout() {
		loginType = "";
		displayView(0);
	}
	public void onDocLogout() {
		loginType = "";
		app.getDoctorinfo().setSession(false);
		displayView(0);
	}
	@Override
	public void loadHomeFragment() {
		displayView(0);
	}
}
