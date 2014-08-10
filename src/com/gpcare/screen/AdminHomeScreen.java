package com.gpcare.screen;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gpcare.fragment.admin.AdminLoginFragment;
import com.gpcare.fragment.admin.AdminRegisterPatirntFragment;
import com.gpcare.fragment.admin.AdminRepeatPrescriptionFragment;
import com.gpcare.fragment.admin.AdminUpadteStaffProfileFragment;
import com.gpcare.fragment.admin.AdminUpdateDoctorsAvailabalityFragment;
import com.gpcare.fragment.admin.AdminUpdateMessageBoardFragment;
import com.gpcare.fragment.admin.AdminViewQueryFragment;
import com.gpcare.model.admin.SignUpView.onAdminSignUpForuserListener;

public class AdminHomeScreen extends BaseScreen implements onAdminSignUpForuserListener {
	private DrawerLayout mDrawerLayout;
	private RelativeLayout list_slidermenu = null;
	private ImageView iv_slider = null,iv_slider_slide = null;
	private LinearLayout ll_profile, ll_register_patient,
			/*ll_update_patients_profile,*/ ll_staff_profile,
			ll_doctor_availability, ll_message_board, ll_repeat_prescription,
			ll_query/*, ll_write_back_to_user*/;
	
	private TextView tv_page_title;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_admin);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		list_slidermenu = (RelativeLayout)findViewById(R.id.list_slidermenu);
		iv_slider = (ImageView)findViewById(R.id.iv_slide);
		iv_slider_slide = (ImageView)findViewById(R.id.iv_slider_slide);
		
		ll_profile = (LinearLayout)findViewById(R.id.ll_profile);
		ll_register_patient = (LinearLayout)findViewById(R.id.ll_register_patient);
		//ll_update_patients_profile = (LinearLayout)findViewById(R.id.ll_update_patients_profile);
		ll_staff_profile = (LinearLayout)findViewById(R.id.ll_staff_profile);
		ll_doctor_availability = (LinearLayout)findViewById(R.id.ll_doctor_availability);
		ll_message_board = (LinearLayout)findViewById(R.id.ll_message_board);
		ll_repeat_prescription = (LinearLayout)findViewById(R.id.ll_repeat_prescription);
		ll_query = (LinearLayout)findViewById(R.id.ll_query);
		//ll_write_back_to_user = (LinearLayout)findViewById(R.id.ll_write_back_to_user);
		tv_page_title = (TextView)findViewById(R.id.tv_page_title);
		
		iv_slider.setOnClickListener(this);
		iv_slider_slide.setOnClickListener(this);
		mDrawerLayout.closeDrawers();	
		
		ll_profile.setOnClickListener(this);
		ll_register_patient.setOnClickListener(this);
		//ll_update_patients_profile.setOnClickListener(this);
		ll_staff_profile.setOnClickListener(this);
		ll_doctor_availability.setOnClickListener(this);
		ll_message_board.setOnClickListener(this);
		ll_repeat_prescription.setOnClickListener(this);
		ll_query.setOnClickListener(this);
		//ll_write_back_to_user.setOnClickListener(this);
		
		displayView(0);
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
		case R.id.ll_profile:
			
			displayView(0);
			break;
		case R.id.ll_register_patient:
			if(app.getAdmininfo().session){
				displayView(1);	
			}else{
				displayView(0);
			}
			
			break;
		case R.id.ll_staff_profile:
			if(app.getAdmininfo().session){
				displayView(2);	
			}else{
				displayView(0);
			}
			break;
		case R.id.ll_doctor_availability:
			if(app.getAdmininfo().session){
				displayView(3);	
			}else{
				displayView(0);
			}
			break;
		case R.id.ll_message_board:
			if(app.getAdmininfo().session){
				displayView(4);	
			}else{
				displayView(0);
			}
			break;
		case R.id.ll_repeat_prescription:
			if(app.getAdmininfo().session){
				displayView(5);	
			}else{
				displayView(0);
			}
			break;
		case R.id.ll_query:
			if(app.getAdmininfo().session){
				displayView(6);	
			}else{
				displayView(0);
			}
			break;
		}
	}
	
	private void displayView(int position) {
		
		Fragment fragment = null;
		switch (position) {
		case 0:
			tv_page_title.setText("Admin Profile");
			fragment = new AdminLoginFragment(this);
			break;
		case 1:
			fragment = new AdminRegisterPatirntFragment(this);
			break;
		case 2:
			fragment = new AdminUpadteStaffProfileFragment(this);
			break;
		case 3:
			fragment = new AdminUpdateDoctorsAvailabalityFragment(this);
			break;
		case 4:
			fragment = new AdminUpdateMessageBoardFragment(this);
			break;
		case 5:
			fragment = new AdminRepeatPrescriptionFragment(this);
			break;
		case 6:
			fragment = new AdminViewQueryFragment(this);
			break;
		}

		if (fragment != null) {
			FragmentManager fragmentManager = getSupportFragmentManager();
			fragmentManager.beginTransaction().replace(R.id.frame_admin, fragment).commit();
			mDrawerLayout.closeDrawers();
		} else {
			
			Log.e("MainActivity", "Error in creating fragment");
		}
	}

	public void onSignU() {
		displayView(1);	
	}
	public void onLogout() {
		app.getAdmininfo().setSession(false);
		displayView(0);
	}
	
	public void onUpdateAdminProfile() {
		displayView(0);
	}
	public void onAdminSignIn(String id,String fname, String lname, String email, String image) {
		displayView(0);
	}
}
