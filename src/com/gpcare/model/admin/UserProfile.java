package com.gpcare.model.admin;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gpcare.screen.BaseScreen;
import com.gpcare.screen.R;
import com.gpcare.settings.ImageLoader;

public class UserProfile implements OnClickListener {
	public BaseScreen base;
	public View mView;
	private ImageView iv_profile_image,iv_settings;
	private TextView tv_user_name,tv_profile_location,tv_profile_email,tv_dob,tv_contact,tv_emergency_contact;
	public ImageLoader imageloader;
	String imagepath,name,address,dob,email,contact,conf_contact;
	public RelativeLayout rl_popup,rl_cointainer;
	private boolean isVisiable = false;
	private String reg_id;
	
	
	public UserProfile(BaseScreen b,String reg_id,String imagepath,String name, String email,String address,String dob,String contact,String conf_contact){
		this.base = b;
		this.reg_id  = reg_id;
		mView = View.inflate(base, R.layout.admin_user_profile, null);
		iv_profile_image = (ImageView)mView.findViewById(R.id.iv_profile_image);
		tv_user_name = (TextView)mView.findViewById(R.id.tv_user_name);
		tv_profile_location = (TextView)mView.findViewById(R.id.tv_profile_location);
		tv_profile_email = (TextView)mView.findViewById(R.id.tv_profile_email);
		tv_dob = (TextView)mView.findViewById(R.id.tv_dob);
		tv_contact = (TextView)mView.findViewById(R.id.tv_contact);
		tv_emergency_contact = (TextView)mView.findViewById(R.id.tv_emergency_contact);
		
		rl_popup = (RelativeLayout)mView.findViewById(R.id.rl_popup);
		rl_popup.setVisibility(View.GONE);
		
	
		iv_settings = (ImageView)mView.findViewById(R.id.iv_settings);
		iv_settings.setOnClickListener(this);
		
		rl_cointainer = (RelativeLayout)mView.findViewById(R.id.rl_cointainer);
		
		this.imagepath = imagepath;
		this.name = name;
		this.email = email;
		this.dob = dob;
		this.contact = contact;
		this.conf_contact = conf_contact;
		this.address = address;
		
		if(!imagepath.equalsIgnoreCase("")){
			imageloader.DisplayImage("", iv_profile_image);
		}
		tv_profile_location.setText(address);
		tv_profile_email.setText(email);
		tv_dob.setText(dob);
		tv_contact.setText(contact);
		tv_emergency_contact.setText(conf_contact);
		tv_user_name.setText(name);
		tv_profile_email.setText(email);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_edit_profile:
			rl_popup.setVisibility(View.GONE);
			isVisiable = false;
			rl_cointainer.removeAllViews();
			//rl_cointainer.addView(new UserEditProfile(base, imagepath, fname, lname, email, address, dob, contact, conf_contact).mView);			
			break;
		case R.id.tv_appointment:
			
			break;
		case R.id.tv_repeat_prescription:
			rl_popup.setVisibility(View.GONE);
			isVisiable = false;
			rl_cointainer.removeAllViews();
		//	rl_cointainer.addView(new UserPrescription(base).mviView);
			break;
		case R.id.tv_logout:
			
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
}
