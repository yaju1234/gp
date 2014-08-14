package com.gpcare.model.doctor;

import org.json.JSONException;
import org.json.JSONObject;

import com.gpcare.constants.Constants;
import com.gpcare.model.SignInListener;
import com.gpcare.network.HttpClient;
import com.gpcare.screen.BaseScreen;
import com.gpcare.screen.R;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class DoctorSignInView implements OnClickListener{
	
	public View mView = null;
	private BaseScreen base;
	private EditText et_username,et_password;
	private Button btn_login;	
	private String userid,fname,lname,email,image,degree,specilization;	
	private int type;
	private SignInListener listener;
	
	public DoctorSignInView(BaseScreen b,SignInListener l){
		base = b;
		listener = l;
		mView = View.inflate(base, R.layout.doctor_signin, null);
		et_username = (EditText)mView.findViewById(R.id.et_username);
		et_password = (EditText)mView.findViewById(R.id.et_password);
		btn_login  = (Button)mView.findViewById(R.id.btn_login);
		btn_login.setOnClickListener(this);
	}
	
	public boolean isvalidLogin(){
		boolean flag = true;
		if(et_username.getText().toString().trim().length()==0){
			et_username.setError("Please enter username");
			flag = false;
			
		}else if(et_password.getText().toString().trim().length()==0){
			et_password.setError("Please enter password");
			flag = false;
		}
		return flag;
	}
	
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_login:
			if(isvalidLogin())
			doSignin();
			break;
		}
	}

	private void doSignin() {
		Thread t = new Thread(){
			public void run(){
				base.doShowLoading();
				callWebService();
				base.doRemoveLoading();
			}						
		};
		t.start();
	}
	
	private void callWebService() {
		String username = et_username.getText().toString().trim();
		String password = et_password.getText().toString().trim();
		
		JSONObject request = new JSONObject();
		try {
			request.put("username", username);
			request.put("password", password);
			request.put("type", 2);
		
			String response = HttpClient.SendHttpPost(Constants.ADMIN_LOGIN, request.toString());
			if(response != null){
				JSONObject jsonres = new JSONObject(response);
				if(jsonres.getBoolean("status")){
					
					userid = jsonres.getString("id");
					fname  = jsonres.getString("fname");
					lname  = jsonres.getString("lname");
					email  = jsonres.getString("username");
					degree = jsonres.getString("degree");
					image = jsonres.getString("image");
					specilization = jsonres.getString("specialization");
					base.app.getDoctorinfo().SetDoctorInfo(userid, fname, lname, email, image,degree, specilization, true);
				updateUi();
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	private void updateUi() {
		base.runOnUiThread(new Runnable() {			
			public void run() {				
				listener.onCallToDoctorProfile(userid,fname,lname,email,image,degree,specilization);			
			}
		});
	}
}
