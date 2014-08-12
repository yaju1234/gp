package com.gpcare.fragment;

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
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class InformationFragment extends Fragment{
	public BaseScreen base;
	private TextView tv_gp,tv_lc,tv_post_date,tv_govt;
	private LinearLayout ll_infozone_update;
	
	@SuppressLint("ValidFragment")
	public InformationFragment(BaseScreen b){
		base = b;
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment_infozone, container, false);
		
		ll_infozone_update = (LinearLayout)view.findViewById(R.id.ll_infozone_update);
		tv_gp = (TextView)view.findViewById(R.id.tv_gp);
		tv_lc = (TextView)view.findViewById(R.id.tv_lc);
		tv_govt = (TextView)view.findViewById(R.id.tv_govt);
		
		getAllNews();
			
		return view;
	}
	
	private void getAllNews() {
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
			ob.put("id", "10");
			
			String response = HttpClient.SendHttpPost(Constants.UPDATE_TONEWS, ob.toString());
			if(response != null){
				
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
	}
}
