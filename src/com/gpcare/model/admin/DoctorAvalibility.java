package com.gpcare.model.admin;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.gpcare.adapter.SlotAdapter;
import com.gpcare.bean.DoctorBean;
import com.gpcare.bean.SlotBean;
import com.gpcare.constants.Constants;
import com.gpcare.network.HttpClient;
import com.gpcare.screen.BaseScreen;
import com.gpcare.screen.R;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class DoctorAvalibility implements OnClickListener{
	
	public View view = null;
	private BaseScreen base;
	private Spinner spinner_date;
	private Calendar cal;
	private List<String> list_date = new ArrayList<String>();
	
	private ArrayList<SlotBean> slotarray = new ArrayList<SlotBean>();
	
	private Button btn_load;
	public String[] myStringArray ;
	public String[] disableArray ;
	
	private SlotAdapter adapter;
	private int pos_date;
	
	private GridView grid;
	
	private LinearLayout ll_container;
	private String id;
	
	
	public DoctorAvalibility(BaseScreen b, String id){
		base  = b;
		this.id = id;
		
		cal = Calendar.getInstance();
		for(int i= 0; i<30; i++){
			cal.add(Calendar.DATE, +i);
			list_date.add(new SimpleDateFormat( "dd-MM-yyyy").format(cal.getTime()));
		}
		view = View.inflate(base, R.layout.update_fragment_doc_availabilty_list, null);
		spinner_date = (Spinner)view.findViewById(R.id.spinner_date);
		
		btn_load = (Button)view.findViewById(R.id.btn_load);
		btn_load.setOnClickListener(this);
		
		ll_container = (LinearLayout)view.findViewById(R.id.ll_container);
		ll_container.setVisibility(View.INVISIBLE);
		
		grid = (GridView)view.findViewById(R.id.gridView1);
		
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
		case R.id.btn_load:
			getBookStatusArray();
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
			ob.put("doc_id",id );
			String response = HttpClient.SendHttpPost(Constants.FETCH_BOOKED_SLOT, ob.toString());
			
			if(response != null){
				slotarray.clear();
				JSONObject obj = new JSONObject(response);
				if(obj.getBoolean("status")){
					JSONObject objct = obj.getJSONObject("slotsarray");
					JSONArray arr = objct.getJSONArray("enabledArray");
					myStringArray = new String[arr.length()];
					for(int i = 0;i<arr.length();i++){
						JSONObject object = arr.getJSONObject(i);
						myStringArray[i] = object.getString("slot");
					}
					JSONArray arr2 = objct.getJSONArray("disabledArray");
					disableArray = new String[arr2.length()];
					for(int k = 0;k<arr2.length();k++){
						JSONObject object = arr2.getJSONObject(k);
						disableArray[k] = object.getString("slot");
					}
					for(int j = 1;j<=20;j++){
						int id = j;
						String types = "open";
						boolean flag = false;
						for(int k = 0;k<myStringArray.length;k++){
							if(j == Integer.parseInt(myStringArray[k])){
								types = "booked";
								flag = true;
							}
						}
						if(!flag){
							for(int a = 0;a<disableArray.length;a++){
								if(j == Integer.parseInt(disableArray[a])){
									types = "disable";
								}
							}
						}else {
							flag = false;
						}
						slotarray.add(new SlotBean(id, types));
					}
				}else{
					for(int m = 1;m<= 20;m++){
						slotarray.add(new SlotBean(m, "open"));
					}
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
				adapter = new SlotAdapter(base, R.layout.grid_row, slotarray,list_date.get(pos_date),id);
				grid.setAdapter(adapter);
				ll_container.setVisibility(View.VISIBLE);
			}
		});
		
	}

}
