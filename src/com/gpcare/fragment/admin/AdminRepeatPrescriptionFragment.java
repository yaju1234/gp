package com.gpcare.fragment.admin;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.gpcare.adapter.PrescriptionAdapter;
import com.gpcare.bean.PrescriptionBean;
import com.gpcare.constants.Constants;
import com.gpcare.network.HttpClient;
import com.gpcare.screen.BaseScreen;
import com.gpcare.screen.R;

@SuppressLint("ValidFragment")
public class AdminRepeatPrescriptionFragment extends Fragment{
	
	private BaseScreen base;
	private ListView ll_prescription;
	private PrescriptionAdapter adapter;
	private ArrayList<PrescriptionBean> list = new ArrayList<PrescriptionBean>();
	
	public AdminRepeatPrescriptionFragment(BaseScreen b){
		base = b;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.admin_prescription_review, container, false);
		ll_prescription = (ListView)view.findViewById(R.id.lv_prescriptionlist);
		
		getAllPrescription();
		return view;
	}

	private void getAllPrescription() {
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
		JSONObject ob = new JSONObject();
		try {
			ob.put("id", "10");
			String response = HttpClient.SendHttpPost(Constants.ALL_PRESCRIPTION_LIST, ob.toString());
			if(response != null){
				JSONObject obj = new JSONObject(response);
				JSONArray arr = obj.getJSONArray("prescription_list");
				for(int i = 0;i<arr.length();i++){
					JSONObject object = arr.getJSONObject(i);
					list.add(new PrescriptionBean(object.getString("item_name"),
							object.getString("qty_ordered"),
							object.getString("date_ordered"),
							object.getString("delivery_date")));
				}
				updateUi();
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	private void updateUi() {
		base.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				adapter = new PrescriptionAdapter(base, R.layout.user_repeat_prescription, list);
				ll_prescription.setAdapter(adapter);
			}
		});
	}
}
