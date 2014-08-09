package com.gpcare.model.admin;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.gpcare.constants.Constants;
import com.gpcare.network.HttpClient;
import com.gpcare.screen.BaseScreen;
import com.gpcare.screen.R;

public class StuffSignUpView implements OnClickListener{
	
	public View mView;
	private BaseScreen base;
	private Button btn_register;
	private EditText et_gp_np,et_fname,et_lname,et_email,et_dob,et_password,et_conf_psw,et_address_info,et_contact_info,et_conf_contact_info;

	private String registration_no,fname,lname,email,dob,password,conf_password,address,contac_info,conf_contac_info;
	public boolean isClicked = false;
	private Spinner spinner1;
	private List<String> list = new ArrayList<String>();
	private String type = "doctor"; 
	
	public StuffSignUpView(BaseScreen b){
		base = b;
		mView = View.inflate(base, R.layout.create_doc_profile, null);
		et_gp_np = (EditText)mView.findViewById(R.id.et_gp_np);
		et_fname = (EditText)mView.findViewById(R.id.et_first_name);
		et_lname = (EditText)mView.findViewById(R.id.et_lanme);
		et_dob = (EditText)mView.findViewById(R.id.et_dob);
		et_email = (EditText)mView.findViewById(R.id.et_email);
		et_password = (EditText)mView.findViewById(R.id.et_password);
		et_conf_psw = (EditText)mView.findViewById(R.id.et_conf_password);
		et_address_info = (EditText)mView.findViewById(R.id.et_address_info);
		et_contact_info = (EditText)mView.findViewById(R.id.et_contact);
		et_conf_contact_info = (EditText)mView.findViewById(R.id.et_emergency_contact);
		
		btn_register = (Button)mView.findViewById(R.id.btn_register);
		btn_register.setOnClickListener(this);
		spinner1 = (Spinner)mView.findViewById(R.id.spinner1);
	
		
	}
	
	public boolean isValidSignup(){
		boolean flag= true;
		registration_no = et_gp_np.getText().toString().trim();
		fname = et_fname.getText().toString().trim();
		lname = et_lname.getText().toString().trim();
		email = et_email.getText().toString().trim();
		dob = et_dob.getText().toString().trim();
		password = et_password.getText().toString().trim();
		conf_password = et_conf_psw.getText().toString().trim();
		address = et_address_info.getText().toString().trim();
		contac_info = et_contact_info.getText().toString().trim();
		conf_contac_info = et_conf_contact_info.getText().toString().trim();
		
		if(registration_no.length() == 0){
			et_gp_np.setError("Please enter GP Reg. No.");
			isClicked = false;
			flag = false;
		}else if(fname.length() == 0){
			et_fname.setError("Please enter First Name");
			isClicked = false;
			flag = false;
		}else if(lname.length() == 0){
			et_lname.setError("Please enter Last Name");
			isClicked = false;
			flag = false;
		}else if(email.length() == 0){
			et_email.setError("Please enter Email.");
			isClicked = false;
			flag = false;
		}else if(dob.length() == 0){
			et_dob.setError("Please enter D.O.B");
			isClicked = false;
			flag = false;
		}else if(password.length() == 0){
			et_password.setError("Please enter Password");
			isClicked = false;
			flag = false;
		}else if(password.length() < 6){
			et_password.setError("Please enter a password more than 6 digit");
			isClicked = false;
			flag = false;
		}else if(conf_password.length() == 0){
			et_conf_psw.setError("Please enter Password again.");
			isClicked = false;
			flag = false;
		}else if(!conf_password.equalsIgnoreCase(password)){
			isClicked = false;
			et_conf_psw.setError("Confirm password mismatched..");
			flag = false;
		}else if(address.length() == 0){
			et_address_info.setError("Please enter Address info.");
			isClicked = false;
			flag = false;
		}else if(contac_info.length() == 0){
			et_contact_info.setError("Please enter contact No.");
			isClicked = false;
			flag = false;
		}else if(conf_contac_info.length() == 0){
			et_conf_contact_info.setError("Please enter Contact No. again");
			isClicked = false;
			flag = false;
		}
		return flag;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_register:
			if(!isClicked){
				isClicked = true;
				if(isValidSignup()){
					doSignUp();
				}
			}
			break;
		}
	}

	private void doSignUp() {
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
		
		try {
			JSONObject object = new JSONObject();
			object.put("type", registration_no);
			object.put("fname", fname);
			object.put("lname", lname);
			object.put("email", email);
			object.put("dob", dob);
			
			object.put("password", password);
			object.put("address",address );
			object.put("contact_info", contac_info);
			object.put("conf_contact_info", conf_contac_info);
			
			String response = HttpClient.SendHttpPost(Constants.STUFF_REGISTER, object.toString());
			if(response != null){
				JSONObject ob = new JSONObject(response);
				if(ob.getBoolean("status")){
					isClicked = false;
					updateUi(ob.getBoolean("status"));
				}else if(!ob.getBoolean("status")){
					updateUi(ob.getBoolean("status"));
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
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
							isClicked = false;				
							dialog.cancel();							
						}
					});					
					dialog.show();
				}else{
					Toast.makeText(base, "Email id already exist", Toast.LENGTH_SHORT).show();
					isClicked = false;
				}				
			}
		});
	}
	
	 public void addItemsOnSpinner() {		 
			
			list.add("Doctor");
			list.add("Nurse");
			list.add("Stuff");
			ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(base,
				android.R.layout.simple_spinner_item, list);
			dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spinner1.setAdapter(dataAdapter);
			spinner1.setOnItemSelectedListener(new OnItemSelectedListener() {				
				public void onItemSelected(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					
					if(arg2== 0){
						type = "doctor";
					}else if(arg2== 1){
						type = "nurse";
					}else{
						type = "stuff";
					}
				}

				
				public void onNothingSelected(AdapterView<?> arg0) {
					
					
				}
			});
		  }
}
