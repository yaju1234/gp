package com.gpcare.model.admin;

import org.json.JSONException;
import org.json.JSONObject;

import com.gpcare.constants.Constants;
import com.gpcare.network.HttpClient;
import com.gpcare.screen.BaseScreen;
import com.gpcare.screen.R;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignInView implements OnClickListener{
	
	public interface OnAdminSignInListener{
		public void onAdminSignIn(String id,String fname, String lname, String email, String image);
	}
	
	public View mView = null;
	private BaseScreen base;
	private EditText et_username,et_password;
	private Button btn_login;	
	private String userid,fname,lname,email,image;	
	private int type;
	private OnAdminSignInListener listener;
	public SignInView(BaseScreen b){
		base = b;
		listener = (OnAdminSignInListener)base;
		mView = View.inflate(base, R.layout.admin_signin, null);
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
			request.put("type", 3);
		
			String response = HttpClient.SendHttpPost(Constants.ADMIN_LOGIN, request.toString());
			if(response != null){
				JSONObject jsonres = new JSONObject(response);
				if(jsonres.getBoolean("status")){
					userid = jsonres.getString("id");
					fname  = jsonres.getString("fname");
					lname  = jsonres.getString("lname");
					email  = jsonres.getString("email");
					image = jsonres.getString("image");
				base.app.getAdmininfo().SetAdminInfo(userid,email, fname, lname, image);
				base.app.getAdmininfo().setSession(true);	
				updateUi();
				}else{
					base.runOnUiThread(new Runnable() {
						@Override
						public void run() {
							Toast.makeText(base, "Invalid User Id or Password", 3000).show();
						}
					});
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
	}

	private void updateUi() {
		base.runOnUiThread(new Runnable() {			
			public void run() {				
				listener.onAdminSignIn(userid,email, fname, lname, image);				
			}
		});
	}
}
