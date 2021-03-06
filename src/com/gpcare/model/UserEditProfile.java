package com.gpcare.model;

import org.json.JSONException;
import org.json.JSONObject;

import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gpcare.constants.Constants;
import com.gpcare.fragment.HomeFragment;
import com.gpcare.network.HttpClient;
import com.gpcare.screen.BaseScreen;
import com.gpcare.screen.R;
import com.gpcare.settings.ImageLoader;

public class UserEditProfile implements OnClickListener{

	public interface UserProfileBackListener {
		public void onDoneClick(BaseScreen b,UserProfileListener listener,String imagepath,String fname,String lname, String email,String address,String dob,String contact,String conf_contact);
	}
	
	public BaseScreen base;
	public View mView;
	private UserProfileBackListener listenner;
	
	private ImageView iv_profile_image;
	private TextView tv_dob;
	private EditText et_profile_location,et_profile_email,et_contact,et_emergency_contact;
	public ImageLoader imageloader;
	String imagepath,fname,lname,address,dob,email,contact,conf_contact;
	private Button btn_update,btn_done;
	private boolean isClicked = false;
	private UserProfileListener listenerprofile;
	private HomeFragment fragment;
	
	public UserEditProfile(BaseScreen b,UserProfileListener listenerprofile,HomeFragment fragment,String imagepath,String fname,String lname, String email,String address,String dob,String contact,String conf_contact){
		this.base = b;
		this.listenerprofile = listenerprofile;
		listenner = (UserProfileBackListener) fragment;
		mView = View.inflate(base, R.layout.user_update_profile, null);
		iv_profile_image = (ImageView)mView.findViewById(R.id.iv_profile_image);
		et_profile_location = (EditText)mView.findViewById(R.id.et_profile_location);
		et_profile_email = (EditText)mView.findViewById(R.id.et_profile_email);
		tv_dob = (TextView)mView.findViewById(R.id.tv_dob);
		et_contact = (EditText)mView.findViewById(R.id.et_contact);
		et_emergency_contact = (EditText)mView.findViewById(R.id.et_emergency_contact);
		this.imagepath = imagepath;
		
		btn_update = (Button)mView.findViewById(R.id.btn_update);
		btn_update.setOnClickListener(this);
		
		btn_done = (Button)mView.findViewById(R.id.btn_done);
		btn_done.setOnClickListener(this);
		
		if(!imagepath.equalsIgnoreCase("")){
			imageloader.DisplayImage("", iv_profile_image);
		}
		et_profile_location.setText(address);
		et_profile_email.setText(email);
		tv_dob.setText(dob);
		et_contact.setText(contact);
		et_emergency_contact.setText(conf_contact);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_update:
			if(!isClicked){
				isClicked = true;
				if(isValid()){
					doUpdateProfile();
				}
			}
			break;		
		case R.id.btn_done:
			listenner.onDoneClick(base, listenerprofile, imagepath, fname, lname, email, address, dob, contact, conf_contact);
		}
	}
	private void doUpdateProfile() {
		Thread t = new Thread(){
			public void run(){
				base.doShowLoading();
				callServerForUpdate();
				base.doRemoveLoading();
			}
		};
		t.start();
	}
	
	private void callServerForUpdate() {
		
		try {
			JSONObject object = new JSONObject();
			object.put("user_id", base.app.getUserinfo().user_id);	
			object.put("email", email);		
			object.put("address",address );
			object.put("contact_info", contact);
			object.put("conf_contact_info", conf_contact);
			
			String response = HttpClient.SendHttpPost(Constants.USER_UPDATE_PROFILE, object.toString());
			if(response != null){
				JSONObject ob = new JSONObject(response);
				if(ob.getBoolean("status")){
					imagepath = ob.getString("image");
					fname = ob.getString("fname");
					lname = ob.getString("lname");
					dob = ob.getString("dob");
					address = ob.getString("address");
					contact = ob.getString("contact");
					email = ob.getString("username");
					conf_contact = ob.getString("emrg_contact");
					updateUi();
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	private void updateUi() {
		base.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				Toast.makeText(base, "Your profile has successfully Updated ", Toast.LENGTH_SHORT).show();
			}
		});
		
	}

	private boolean isValid(){
		boolean flag = true;
		address = et_profile_location.getText().toString().trim();
		email = et_profile_email.getText().toString().trim();
		contact = et_contact.getText().toString().trim();
		conf_contact = et_emergency_contact.getText().toString().trim();
		
		if(address.length() == 0){
			et_profile_location.setError("Please enter your location");
			isClicked = false;
			flag = false;
		}else if(email.length() == 0){
			et_profile_email.setError("Please enter your email");;
			isClicked = false;
			flag = false;
		}else if(contact.length() == 0){
			et_contact.setError("Please enter your contact");;
			flag = false;
			isClicked = false;
			
		}else if(conf_contact.length() == 0){
			et_emergency_contact.setError("Please enter your emerency contact");;
			flag = false;
			isClicked = false;
		}
		return flag;
	}
}
