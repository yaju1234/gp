package com.gpcare.model;

import org.json.JSONException;
import org.json.JSONObject;

import com.gpcare.constants.Constants;
import com.gpcare.network.HttpClient;
import com.gpcare.screen.BaseScreen;
import com.gpcare.screen.R;

import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SignInView implements OnClickListener{
	
	public View mView = null;
	private BaseScreen base;
	private EditText et_username,et_password;
	private Button btn_login,btn_signup;
	private SignInListener listener;
	private SignUpListener signuplistener;
	private boolean flag;
	
	private String userid,fname,lname,email,image,dob,address,contact,emg_contact;
	
	private TextView tv_app_text;
	private LinearLayout ll_signin;
	private int type;
	
	public SignInView(BaseScreen b,SignInListener l,SignUpListener sl,boolean flag,int type){
		base = b;
		listener = l;
		this.type = type;
		signuplistener = sl;
		this.flag = flag;
		
		mView = View.inflate(base, R.layout.signin, null);
		et_username = (EditText)mView.findViewById(R.id.et_username);
		et_password = (EditText)mView.findViewById(R.id.et_password);
		btn_login  = (Button)mView.findViewById(R.id.btn_login);
		btn_signup  = (Button)mView.findViewById(R.id.btn_signup);
		tv_app_text = (TextView)mView.findViewById(R.id.tv_app_text);
		ll_signin = (LinearLayout)mView.findViewById(R.id.ll_signin);
		
		if(this.flag){
			tv_app_text.setVisibility(View.VISIBLE);
			ll_signin.setGravity(Gravity.CENTER|Gravity.TOP);
		}else{
			tv_app_text.setVisibility(View.INVISIBLE);
			ll_signin.setGravity(Gravity.CENTER);
		}
		if(type == 3 ){
			btn_signup.setVisibility(View.GONE);
		}
		
		btn_login.setOnClickListener(this);
		btn_signup.setOnClickListener(this);
		
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

		case R.id.btn_signup:
			signuplistener.OnSignUp();
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
			request.put("type", type);
			
			String url = null;
			if(type == 1){
				url = Constants.ADMIN_LOGIN;
			}else if(type == 2){
				url = Constants.ADMIN_LOGIN;
			}else{
				url = Constants.ADMIN_LOGIN;
			}
			String response = HttpClient.SendHttpPost(url, request.toString());
			if(response != null){
				JSONObject jsonres = new JSONObject(response);
				if(jsonres.getBoolean("status")){
					userid = jsonres.getString("id");
					fname  = jsonres.getString("fname");
					lname  = jsonres.getString("lname");
					email  = jsonres.getString("email");
					
					if(type == 1 ){			
					image  = jsonres.getString("image");
					dob  = jsonres.getString("dob");
					address  = jsonres.getString("address");
					contact  = jsonres.getString("contact");
					emg_contact  = jsonres.getString("emrg_contact");
					base.app.getUserinfo().SetUserInfo(userid, fname, lname,email,  address, image,dob);
					}
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
				if(type == 1 ){	
					listener.onUserSignIn(userid,fname,lname,email,image,dob,address,contact,emg_contact);
				}else if(type == 3){
					listener.onAdminLogin(fname, lname, email);
				}
			}
		});
	}
}
