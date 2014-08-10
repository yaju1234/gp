package com.gpcare.model.admin;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gpcare.constants.Constants;
import com.gpcare.network.HttpClient;
import com.gpcare.screen.BaseScreen;
import com.gpcare.screen.R;

public class StuffSignUpView implements OnClickListener{
	
	public View mView;
	private BaseScreen base;
	private Button btn_register;
	private EditText et_fname,et_lname,et_email,et_specalization,et_degree,et_password;

	private String fname,lname,email,specialization,degree,password;
	
	
	public StuffSignUpView(BaseScreen b){
		base = b;
		mView = View.inflate(base, R.layout.create_doc_profile, null);
		et_fname = (EditText)mView.findViewById(R.id.et_fname);
		et_lname = (EditText)mView.findViewById(R.id.et_lname);
		et_email = (EditText)mView.findViewById(R.id.et_email);
		et_password = (EditText)mView.findViewById(R.id.et_password);
		et_specalization = (EditText)mView.findViewById(R.id.et_specalization);
		et_degree = (EditText)mView.findViewById(R.id.et_degree);
		et_password = (EditText)mView.findViewById(R.id.et_password);
		
		btn_register = (Button)mView.findViewById(R.id.btn_register);
		btn_register.setOnClickListener(this);
		
		
	}
	
	public boolean isValidSignup(){
		boolean flag= true;
		fname = et_fname.getText().toString().trim();
		lname = et_lname.getText().toString().trim();
		email = et_email.getText().toString().trim();
		specialization = et_specalization.getText().toString().trim();
		degree = et_degree.getText().toString().trim();
		password = et_password.getText().toString().trim();
		
		 if(fname.length() == 0){
			et_fname.setError("Please enter First Name");
			flag = false;
		}else if(lname.length() == 0){
			et_lname.setError("Please enter Last Name");
			flag = false;
		}else if(email.length() == 0){
			et_email.setError("Please enter Email.");
			flag = false;
		}else if(specialization.length() == 0){
			et_specalization.setError("Please enter specalization");
			flag = false;
		}else if(degree.length() == 0){
			et_degree.setError("Please enter degree");
			flag = false;
		}else if(password.length() == 0){
			et_password.setError("Please enter Password");
			flag = false;
		}
		return flag;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_register:
			
				if(isValidSignup()){
					doSignUp();
				}
			
			break;
		}
	}

	private void doSignUp() {
		Thread t = new Thread(){
			public void run(){
				base.doShowLoading();
				callWebService();
				
			}						
		};
		t.start();
	}
	private void callWebService() {
		
		try {
			JSONObject object = new JSONObject();
		object.put("fname", fname);
			object.put("lname", lname);
			object.put("email", email);
			object.put("specialization", specialization);			
			object.put("password", password);
			object.put("degree",degree );
			
			String response = HttpClient.SendHttpPost(Constants.DOCTOR_REGISTER, object.toString());
			if(response != null){
				JSONObject ob = new JSONObject(response);
				if(ob.getBoolean("status")){
					updateUi(ob.getBoolean("status"));
				}else if(!ob.getBoolean("status")){
					updateUi(ob.getBoolean("status"));
				}
			}
			base.doRemoveLoading();
		} catch (JSONException e) {
			e.printStackTrace();
			base.doRemoveLoading();
		}
	}

	private void updateUi(final boolean b) {
		base.runOnUiThread(new Runnable() {		
			@Override
			public void run() {
				if(b){
					final Dialog dialog = new Dialog(base);
					dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
					dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
					dialog.setContentView(R.layout.dialog);
					dialog.setCancelable(true);
					
					final Button btn_ok = (Button)dialog.findViewById(R.id.btn_ok);					
					btn_ok.setOnClickListener(new OnClickListener() {			
						@Override
						public void onClick(View v) {
							dialog.cancel();							
						}
					});					
					dialog.show();
				}else{
					//Toast.makeText(base, "Email id already exist", Toast.LENGTH_SHORT).show();
					
				}				
			}
		});
	}	
	
}
