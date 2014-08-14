package com.gpcare.model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.gpcare.adapter.UserBookedSlotAdapter;
import com.gpcare.bean.PrescriptionBean;
import com.gpcare.bean.UserBookedSlotBean;
import com.gpcare.constants.Constants;
import com.gpcare.fragment.HomeFragment;
import com.gpcare.model.UserPrescription.UserPrescritionBackListener;
import com.gpcare.network.HttpClient;
import com.gpcare.screen.BaseScreen;
import com.gpcare.screen.R;

public class UserBookedSlot implements OnClickListener{

	public interface UserbBookedSlotBackListener {
		public void onBookedSlotDoneClick();
	}
	
	public BaseScreen base;
	public View mviView;
	public ListView ll_list;
	public Button btn_submit,btn_done;
	public ArrayList<UserBookedSlotBean> list = new ArrayList<UserBookedSlotBean>();
	public UserBookedSlotAdapter adapter;
	
	private UserbBookedSlotBackListener listener;
	
	public UserBookedSlot(BaseScreen b, HomeFragment fragment){
		this.base = b;
		mviView = View.inflate(base, R.layout.user_all_booked_appoinment, null);
		ll_list = (ListView)mviView.findViewById(R.id.ll_booked_list);
		
		btn_done = (Button)mviView.findViewById(R.id.btn_done);
		btn_done.setOnClickListener(this);
		listener = (UserbBookedSlotBackListener) fragment;
		getAllBookedList();
	}

	private void getAllBookedList() {
		Thread t = new Thread(){
			public void run(){
				callwebservicetofetch();
			}			
		};
		t.start();
	}
	private void callwebservicetofetch() {
		JSONObject ob = new JSONObject();
		try {
			ob.put("id", base.app.getUserinfo().user_id);
			String response = HttpClient.SendHttpPost(Constants.GET_BOOKED_SLOT, ob.toString());
			if(response != null){
				
				System.out.println("##--reach here"+response);
				list.clear();
				JSONObject obj = new JSONObject(response);
				JSONArray arr = obj.getJSONArray("slotsArray");
				for(int i = 0;i<arr.length();i++){
					JSONObject object = arr.getJSONObject(i);
					list.add(new UserBookedSlotBean(object.getString("appointment_no"),
							object.getString("slot"),
							object.getString("date"),
							object.getString("doctor_name"),
							object.getString("details")));
				}
				populateList();
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	private void populateList() {
		base.runOnUiThread(new Runnable() {			
			@Override
			public void run() {
				adapter = new UserBookedSlotAdapter(base, R.layout.show_booked_slot, list);
				ll_list.setAdapter(adapter);
			}
		});
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_done:
			listener.onBookedSlotDoneClick();
		}
	}
}
