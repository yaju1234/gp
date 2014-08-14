package com.gpcare.model.doctor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

import com.gpcare.adapter.DocAvailAdapter;
import com.gpcare.adapter.SlotAdapter;
import com.gpcare.bean.DocSlotBean;
import com.gpcare.bean.SlotBean;
import com.gpcare.constants.Constants;
import com.gpcare.network.HttpClient;
import com.gpcare.screen.BaseScreen;
import com.gpcare.screen.R;

public class DoctorSlotBooking implements OnClickListener{

	public BaseScreen base;
	public View view;
	private List<String> list_date = new ArrayList<String>();
	private Calendar cal;
	private Spinner spinner_date;
	private int pos_date;
	private LinearLayout listView1;
	private Button button1;
	private DocAvailAdapter adapter;
	private ArrayList<DocSlotBean> ArrDoc = new ArrayList<DocSlotBean>();
	
	public DoctorSlotBooking(BaseScreen b){
		this.base = b;
		view = View.inflate(base, R.layout.doctor_view_appointment, null);
		spinner_date = (Spinner)view.findViewById(R.id.spinner_date);
		listView1  = (LinearLayout)view.findViewById(R.id.listView1);
		button1 = (Button)view.findViewById(R.id.button1);
		button1.setOnClickListener(this);
		cal = Calendar.getInstance();
		for(int i= 0; i<30; i++){
			cal.add(Calendar.DATE, +i);
			list_date.add(new SimpleDateFormat( "dd-MM-yyyy").format(cal.getTime()));
		}
		
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(base,
				android.R.layout.simple_spinner_item, list_date);
			dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spinner_date.setAdapter(dataAdapter);
			spinner_date.setSelected(true);
			spinner_date.setSelection(0);
			spinner_date.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					
					//spinner_date.setSelection(arg2);
					pos_date = arg2;
					spinner_date.setSelection(arg2);
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					
				}
			});
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1:
			getBookStatusArray();
			break;

		default:
			break;
		}
		
	}
	
	private void getBookStatusArray() {
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
			ob.put("date", list_date.get(pos_date));
			ob.put("id",base.app.getDoctorinfo().user_id);
			String response = HttpClient.SendHttpPost(Constants.GET_DOC_SLOT, ob.toString());
			
			if(response != null){
				ArrDoc.clear();
				JSONObject obj = new JSONObject(response);
				//if(obj.getBoolean("status")){
					JSONArray arr = obj.getJSONArray("slotsArray");
					
					for(int i = 0;i<arr.length();i++){
						JSONObject object = arr.getJSONObject(i);
						String name = object.getString("patient_name");
						String date = object.getString("slot");
						String desvc = object.getString("description");
						ArrDoc.add(new DocSlotBean(name, date, desvc));
					}
						
				updateUi();
			//}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	private void updateUi() {
		base.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				listView1.removeAllViews();
				for(int i=0; i<ArrDoc.size(); i++){
					listView1.addView(new DocAvailAdapter(base, ArrDoc.get(i).getName(), ArrDoc.get(i).getTime(), ArrDoc.get(i).getDesc()).v);
				}
				/*adapter = new DocAvailAdapter(base, R.layout.grid_row,ArrDoc);
				listView1.setAdapter(adapter);*/
				
			}
		});
		
	}
}
