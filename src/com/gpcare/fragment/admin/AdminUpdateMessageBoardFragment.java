package com.gpcare.fragment.admin;

import org.json.JSONException;
import org.json.JSONObject;

import com.gpcare.constants.Constants;
import com.gpcare.network.HttpClient;
import com.gpcare.screen.BaseScreen;
import com.gpcare.screen.R;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

@SuppressLint("ValidFragment")
public class AdminUpdateMessageBoardFragment extends Fragment implements OnClickListener{
	
	private BaseScreen base;
	private Button btn_submit1,btn_submit2,btn_submit3;
	private EditText et_text1,et_text2,et_text3;
	
	private String govt_news,local_news,gpcare_news = "";
	
	public AdminUpdateMessageBoardFragment(BaseScreen b){
		base = b;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.admin_message_board, container, false);
		btn_submit1 = (Button)view.findViewById(R.id.btn_submit1);
		btn_submit2 = (Button)view.findViewById(R.id.btn_submit2);
		btn_submit3 = (Button)view.findViewById(R.id.btn_submit3);
		et_text1 = (EditText)view.findViewById(R.id.et_text1);
		et_text2 = (EditText)view.findViewById(R.id.et_text2);
		et_text3 = (EditText)view.findViewById(R.id.et_text3);
		btn_submit1.setOnClickListener(this);
		btn_submit2.setOnClickListener(this);
		btn_submit3.setOnClickListener(this);
		return view;
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_submit1:
			govt_news = et_text1.getText().toString().trim();
			if(govt_news.length() > 0){
				sendUpdateToserver();
			}else{
				et_text1.setError("Please Enter govt news update");
			}
			
			break;
		case R.id.btn_submit2:
			gpcare_news = et_text2.getText().toString().trim();
			if(gpcare_news.length() > 0){
				sendUpdateToserver();
			}else{
				et_text2.setError("Please Enter gp care update");
			}
			break;

		case R.id.btn_submit3:
			local_news = et_text3.getText().toString().trim();
			if(local_news.length() > 0){
				sendUpdateToserver();
			}else{
				et_text3.setError("Please Enter local news update");
			}
			break;
		}
	}

	private void sendUpdateToserver() {
		Thread t = new Thread(){
			public void run(){
				base.doShowLoading();
				callwebservice();
				base.doRemoveLoading();
			}
		};
		t.start();
	}
	
	private void callwebservice() {
		try {
			JSONObject ob = new JSONObject();
			ob.put("gp_news", gpcare_news);
			ob.put("local_community", local_news);
			ob.put("govt_news", govt_news);
			ob.put("id", "1");
			
			String response = HttpClient.SendHttpPost(Constants.UPDATE_TONEWS, ob.toString());
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
				Toast.makeText(base, "Successfully submitted", 4000).show();
			}
		});
	}
}
