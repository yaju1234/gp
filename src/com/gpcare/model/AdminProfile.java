package com.gpcare.model;

import com.gpcare.model.admin.EditAdminProfileView;
import com.gpcare.screen.BaseScreen;
import com.gpcare.screen.R;

import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AdminProfile implements OnClickListener {
	
	public interface OnAdminListeber{
		public void onLogout();
	}
	public View mView = null;
	public BaseScreen base;
	public String fname;
	public String lname;
	public String  email;
	
	private TextView tv_name, tv_email;
	private Button btn_logout,btn_edit_profile;
	private OnAdminListeber listener;
	private RelativeLayout rl_header;
	
	
	public AdminProfile(BaseScreen b,String id,String fname, String lname, String email, String image){
		base = b;
		listener = (OnAdminListeber) base;
		this.fname = fname;
		this.email = email;
		this.lname = lname;
		mView  =View.inflate(base, R.layout.admin_profile, null);
		tv_name = (TextView)mView.findViewById(R.id.tv_admin_name);
		tv_email = (TextView)mView.findViewById(R.id.tv_admin_email);
		btn_logout = (Button)mView.findViewById(R.id.btn_logout);
		btn_logout = (Button)mView.findViewById(R.id.btn_logout);
		btn_edit_profile = (Button)mView.findViewById(R.id.btn_edit_profile);
		rl_header = (RelativeLayout)mView.findViewById(R.id.rl_header);
		tv_name.setText(fname+" "+lname);
		tv_email.setText(email);
		btn_logout.setOnClickListener(this);
		btn_edit_profile.setOnClickListener(this);
	}


	
	public void onClick(View v) {
	 switch (v.getId()) {
	case R.id.btn_logout:
		listener.onLogout();
		break;
	case R.id.btn_edit_profile:
		rl_header.removeAllViews();
		rl_header.addView(new EditAdminProfileView(base).mView);
		break;

	}
		
	}

}
