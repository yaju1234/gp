package com.gpcare.model;

import com.gpcare.screen.BaseScreen;
import com.gpcare.screen.R;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AdminProfile implements OnClickListener {
	public View mView = null;
	public BaseScreen base;
	public String fname;
	public String lname;
	public String  email;
	
	private TextView tv_name, tv_email;
	private Button btn_logout;
	
	
	public AdminProfile(BaseScreen b,String fname, String lname, String email){
		base = b;
		this.fname = fname;
		this.email = email;
		this.lname = lname;
		Toast.makeText(base, "aaa", 3000).show();
		mView  =View.inflate(base, R.layout.admin_profile, null);
		tv_name = (TextView)mView.findViewById(R.id.tv_admin_name);
		tv_email = (TextView)mView.findViewById(R.id.tv_admin_email);
		btn_logout = (Button)mView.findViewById(R.id.btn_logout);
		tv_name.setText(fname+" "+lname);
		tv_email.setText(email);
		btn_logout.setOnClickListener(this);
	}


	
	public void onClick(View v) {
	 switch (v.getId()) {
	case R.id.btn_logout:
		
		break;

	}
		
	}

}
