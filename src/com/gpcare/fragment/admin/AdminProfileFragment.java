package com.gpcare.fragment.admin;

import com.gpcare.constants.Constants;
import com.gpcare.model.AdminProfile;
import com.gpcare.model.SignInListener;
import com.gpcare.model.SignInView;
import com.gpcare.model.SignUpListener;
import com.gpcare.screen.BaseScreen;
import com.gpcare.screen.R;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

@SuppressLint("ValidFragment")
public class AdminProfileFragment extends Fragment implements SignInListener,SignUpListener{
	
	private BaseScreen base;
	private LinearLayout ll_admin_profile = null;
	
	public AdminProfileFragment(BaseScreen b){
		base = b;
	}	
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_admin_profile, container, false);
		ll_admin_profile = (LinearLayout)view.findViewById(R.id.ll_admin_profile);
		ll_admin_profile.removeAllViews();
		ll_admin_profile.addView(new SignInView(base,this,this,false,Constants.ADMIN).mView);
		return view;
	}	
	public void OnSignUp() {}
	public void CallBackFromSignUp() {}
	public void onLodaProfile() {}	
	public void onUserSignIn(String userid, String fname, String lname,
			String email, String image, String dob, String address,
			String contact, String emgcontact) {}
	public void onAdminLogin(String fname, String lname, String email) {
		ll_admin_profile.removeAllViews();
		ll_admin_profile.addView(new AdminProfile(base, fname, lname, email).mView);
	}

}
