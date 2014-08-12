package com.gpcare.fragment.admin;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.gpcare.adapter.QueryAdapter;
import com.gpcare.bean.QueryBean;
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
import android.widget.ListView;

@SuppressLint("ValidFragment")
public class AdminViewQueryFragment extends Fragment{
	
	private BaseScreen base;
	private ListView ll_query_list;
	private ArrayList<QueryBean> list = new ArrayList<QueryBean>();
	private QueryAdapter adapter;
	
	public AdminViewQueryFragment(BaseScreen b){
		base = b;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_query_list, container, false);
		
		ll_query_list = (ListView)view.findViewById(R.id.ll_query_list);
		getAllQueryList();
		
		return view;
	}

	private void getAllQueryList() {
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
			String response = HttpClient.SendHttpPost(Constants.ALLQUERYLIST, ob.toString());
			if(response != null){
				JSONObject obj = new JSONObject(response);
				JSONArray arr = obj.getJSONArray("messages");
				for(int i = 0;i<arr.length();i++){
					JSONObject object = arr.getJSONObject(i);
					list.add(new QueryBean(object.getString("name"),
							object.getString("email"),
							object.getString("message"),
							object.getString("id")));
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
				adapter = new QueryAdapter(base, R.layout.query_row, list);
				ll_query_list.setAdapter(adapter);
			}
		});
	}
}
