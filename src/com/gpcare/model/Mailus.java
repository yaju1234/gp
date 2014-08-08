package com.gpcare.model;

import org.json.JSONException;
import org.json.JSONObject;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.gpcare.constants.Constants;
import com.gpcare.network.HttpClient;
import com.gpcare.screen.BaseScreen;
import com.gpcare.screen.R;

public class Mailus implements OnClickListener {
	public View mView = null;
	private BaseScreen base;
	public EditText et_name,et_email,et_message;
	public LinearLayout ll_send;
	public boolean flag = false;
	public String name,email,message;
	
	public Mailus(BaseScreen b){
		base = b;
		mView = View.inflate(base, R.layout.mail_us, null);
		
		et_name = (EditText)mView.findViewById(R.id.et_name);
		et_email = (EditText)mView.findViewById(R.id.et_email);
		et_message = (EditText)mView.findViewById(R.id.et_message);
		
		ll_send = (LinearLayout)mView.findViewById(R.id.ll_send);
		ll_send.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ll_send:
			if(!flag){
				if(isValidFormFillUp()){
					doMailUs();
				}
			}
			break;

		default:
			break;
		}
	}
	private void doMailUs() {
		Thread t = new Thread(){
			public void run(){
				base.doShowLoading();
				callServer();
				base.doRemoveLoading();
			}			
		};
		t.start();
	}

	private void callServer() {
		
		try {
			JSONObject ob = new JSONObject();
			ob.put("name",name);
			ob.put("email",email);
			ob.put("message",message);
			
			String response = HttpClient.SendHttpPost(Constants.ADD_MESSAGE, ob.toString());
			if(response != null){
				JSONObject obj = new JSONObject(response);
				if(obj.getBoolean("status")){
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
				Toast.makeText(base, "Your message has been successfully submitted", 3000).show();
			}
		});
	}

	public boolean isValidFormFillUp(){
		boolean flag = true;
		name = et_name.getText().toString().trim();
		email = et_email.getText().toString().trim();
		message = et_message.getText().toString().trim();
		
		if(name.length() == 0){
			flag = false;
			et_name.setError("Please Enter your name");
		}else if(email.length() == 0){
			flag = false;
			et_email.setError("Please Enter your mailid");
		}else if(message.length() == 0){
			flag = false;
			et_message.setError("Please Enter post message");
		}
		return flag;
	}
}
