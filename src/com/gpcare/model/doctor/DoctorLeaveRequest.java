package com.gpcare.model.doctor;

import org.json.JSONException;
import org.json.JSONObject;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gpcare.constants.Constants;
import com.gpcare.network.HttpClient;
import com.gpcare.screen.BaseScreen;
import com.gpcare.screen.R;

public class DoctorLeaveRequest implements OnClickListener{
	
	public BaseScreen base;
	public View mView;
	public EditText ed_date,ed_message;
	public Button btn_send,btn_done;
	public String date,message;
	public boolean flag = false;
	public DoctorHome doctorHome;
	
	public DoctorLeaveRequest(BaseScreen b, DoctorHome doctorHome){
		this.base = b;
		this.doctorHome = doctorHome;
		mView = View.inflate(base, R.layout.doctor_leave_req, null);
		
		ed_date = (EditText)mView.findViewById(R.id.et_name);
		ed_message = (EditText)mView.findViewById(R.id.et_email);
		
		btn_send = (Button)mView.findViewById(R.id.btn_send);
		btn_send.setOnClickListener(this);
		
		btn_done = (Button)mView.findViewById(R.id.btn_done);
		btn_done.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_send:
			if(!flag){
				if(isValidFormFillUp()){
					doSendLeaveReq();
				}
			}
			break;

		case R.id.btn_done:
			doctorHome.backDoctorHome();
			break;
		}
	}
	private void doSendLeaveReq() {
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
			ob.put("date",date);
			ob.put("user_id",base.app.getUserinfo().user_id);
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

	public boolean isValidFormFillUp(){
		boolean flag = true;
		date = ed_date.getText().toString().trim();
		message = ed_message.getText().toString().trim();
		
		if(date.length() == 0){
			flag = false;
			ed_date.setError("Please Enter date");
		}else if(message.length() == 0){
			flag = false;
			ed_message.setError("Please Enter your mailid");
		}
		return flag;
	}
	
	private void updateUi() {
		base.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				Toast.makeText(base, "Your message has been successfully submitted", 3000).show();
			}
		});
	}
}
