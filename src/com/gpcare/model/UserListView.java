package com.gpcare.model;

import com.gpcare.screen.BaseScreen;
import com.gpcare.screen.R;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class UserListView implements OnClickListener{
	
	public View mView = null;
	private BaseScreen base;
	private TextView tv_name;
	private String name;
	private LinearLayout ll_user = null;
	private UserListener listener;
	private String regno;
	
	public UserListView(BaseScreen b, String name, String regno,UserListener l){
		base = b;	
		this.name = name;
		listener = l;
		this.regno = regno;
		mView = View.inflate(base, R.layout.user_list, null);
		tv_name = (TextView)mView.findViewById(R.id.tv_name);
		tv_name.setText(name);
		ll_user = (LinearLayout)mView.findViewById(R.id.ll_user);
		ll_user.setOnClickListener(this);
	}
	
	/**
	 *  {"userArray":{"reg_no":"suman","name":"suman saha","username":"saha@gmail.com","dob":"1991-12-10",
	 *  "address":"fhhjhvbjkjgffrtiigrfujhgfffg","contact":"9674448739",
	 *  "emrg_contact":"9674448739","image":""}}

	 */

	
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ll_user:
			listener.onUserListClick(regno);
			break;

		}
		
	}

}
