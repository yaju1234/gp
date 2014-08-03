package com.gpcare.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.gpcare.model.SignInListener;
import com.gpcare.model.SignInView;
import com.gpcare.model.SignUpListener;
import com.gpcare.model.SignUpView;
import com.gpcare.screen.BaseScreen;
import com.gpcare.screen.R;
import com.gpcare.screen.R.id;

public class HomeFragment extends Fragment implements SignUpListener,SignInListener, OnClickListener{
	public BaseScreen base;
	private Button btn_login,btn_register;
	private LinearLayout ll_home;
	private RelativeLayout rl_home;
	
	public HomeFragment(BaseScreen b){
		base = b;
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
		
		return view;
	}

	@Override
	public void onSignIn(String userid, String fname, String lname,
			String image, String dob) {
		
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
			ll_home.addView(new SignInView(base,this,this,false).mView);
			break;

		case R.id.btn_register:
			ll_home.removeAllViews();
			ll_home.setVisibility(View.VISIBLE);
			rl_home.setVisibility(View.GONE);
			ll_home.addView(new SignUpView(base,this,this).mView);
			break;
		}
	}

	@Override
	public void CallBackFromSignUp() {
		ll_home.setVisibility(View.VISIBLE);
		rl_home.setVisibility(View.GONE);
		ll_home.addView(new SignInView(base,this,this,false).mView);
	}
}
