package com.gpcare.fragment.admin;

import com.gpcare.model.AdminProfile;
import com.gpcare.model.admin.SignInView;
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
public class AdminLoginFragment extends Fragment {
	
	private BaseScreen base;
	private LinearLayout ll_admin_profile = null;
	
	public AdminLoginFragment(BaseScreen b){
		base = b;
	}	
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_admin_profile, container, false);
		ll_admin_profile = (LinearLayout)view.findViewById(R.id.ll_admin_profile);
		if(!base.app.getAdmininfo().session){
			ll_admin_profile.removeAllViews();
			ll_admin_profile.addView(new SignInView(base).mView);
		}else{
			onAdminSignIn(base.app.getAdmininfo().user_id, base.app.getAdmininfo().first_name, base.app.getAdmininfo().last_name, base.app.getAdmininfo().email, base.app.getAdmininfo().email);	
		}
		
		return view;
	}	
	public void OnSignUp() {}
	public void CallBackFromSignUp() {}
	public void onLodaProfile() {}	
	public void onAdminLogin(String fname, String lname, String email) {
		
	}
	
	public void onAdminSignIn(String id,String fname, String lname, String email, String image) {
		ll_admin_profile.removeAllViews();
		ll_admin_profile.addView(new AdminProfile(base, id,fname, lname, email, image).mView);
	}

}
