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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class EditAdminProfileView implements OnClickListener{
	
	public interface onUpdateAdminProfileListener{
		public void onUpdateAdminProfile();
	}
	
	public  View mView = null;
	public BaseScreen base;
	private EditText et_fname,et_lname,et_email;
	private ImageView iv_image;
	private Button btn_update;
	private RelativeLayout rl_header;
	private onUpdateAdminProfileListener listener;
	public  EditAdminProfileView(BaseScreen b){
		base = b;
		listener = (onUpdateAdminProfileListener) base;
		mView = View.inflate(base, R.layout.admin_profile_edit, null);
		et_fname = (EditText)mView.findViewById(R.id.et_fname);
		et_lname = (EditText)mView.findViewById(R.id.et_lname);
		et_email = (EditText)mView.findViewById(R.id.et_email);
		btn_update = (Button)mView.findViewById(R.id.btn_update);
		rl_header = (RelativeLayout)mView.findViewById(R.id.rl_header);
		btn_update.setOnClickListener(this);
		et_fname.setText(base.app.getAdmininfo().first_name);
		et_lname.setText(base.app.getAdmininfo().last_name);
		et_email.setText(base.app.getAdmininfo().email);
	}

	
	public void onClick(View v) {
	switch (v.getId()) {
	case R.id.btn_update:
		if(isvalid())
		sendUpdate();
		break;

	}
		
	}
	
	public void sendUpdate(){
		Thread t = new Thread(){
			public void run(){
				base.doShowLoading();
				try {
					JSONObject json  = new JSONObject();
					json.put("fname", et_fname.getText().toString().trim());
					json.put("lname", et_lname.getText().toString().trim());
					json.put("email", et_email.getText().toString().trim());
					String response = HttpClient.SendHttpPost(Constants.EDIT_ADMIN_PROFILE, json.toString());
					if(response != null){
						JSONObject ob = new JSONObject(response);
						if(ob.getBoolean("status")){
							base.app.getAdmininfo().setFirst_name(et_fname.getText().toString().trim());
							base.app.getAdmininfo().setLast_name(et_lname.getText().toString().trim());
							base.app.getAdmininfo().setEmail(et_email.getText().toString().trim());
						}
						updtaeUi();
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		};
		t.start();
	}
	
	public void updtaeUi(){
		base.doRemoveLoading();
		base.runOnUiThread(new Runnable() {
			
			
			public void run() {
				Toast.makeText(base, "Success", Toast.LENGTH_LONG).show();
				listener.onUpdateAdminProfile();
			}
		});
	}
	
	public boolean isvalid(){
		boolean flag = true;
		if(et_fname.getText().toString().trim().length()==0){
			et_fname.setError("Please enter First Name");
			flag = false;
		}else if(et_lname.getText().toString().trim().length()==0){
			et_lname.setError("Please enter last Name");
			flag = false;
		}else if(et_email.getText().toString().trim().length()==0){
			et_email.setError("Please enter Email");
			flag = false;
		}
		return flag;
	}


}
