package com.gpcare.fragment;

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

import android.R.integer;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

@SuppressLint("ValidFragment") 
public class DoctorFragment extends Fragment implements OnClickListener{
	public BaseScreen base;
	private Spinner spinner_date,spinner_doc;
	private Calendar cal;
	private List<String> list_date = new ArrayList<String>();
	private List<String> list_dr = new ArrayList<String>();
	
	private ArrayList<SlotBean> slotarray = new ArrayList<SlotBean>();
	
	private LinearLayout ll_cointainer;
	private Button btn_load;
	public String[] myStringArray ;
	public ArrayList<DoctorBean> docList = new ArrayList<DoctorBean>();
	
	private SlotAdapter adapter;
	private int pos_doc;
	private int pos_date;
	
	private GridView grid;
	
	private LinearLayout ll_container;
	
	@SuppressLint("SimpleDateFormat")
	public DoctorFragment(BaseScreen b){
		base = b;
		cal = Calendar.getInstance();
		for(int i= 0; i<30; i++){
			cal.add(Calendar.DATE, +i);
			list_date.add(new SimpleDateFormat( "dd-MM-yyyy").format(cal.getTime()));
		}
		
		Thread t = new Thread(){
			public void run(){
				getAllstuffList();
			}
		};
		t.start();
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment_doc_availabilty, container, false);
		spinner_date = (Spinner)view.findViewById(R.id.spinner_date);
		spinner_doc = (Spinner)view.findViewById(R.id.spinner_doctor);
		ll_cointainer = (LinearLayout)view.findViewById(R.id.ll_container);
		
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
			
			/*ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(base,
					android.R.layout.simple_spinner_item, list_dr);
			dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				spinner_doc.setAdapter(dataAdapter1);*/
				/*spinner_doc.setSelected(true);
				spinner_doc.setSelection(0);
				spinner_doc.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						spinner_doc.setSelection(arg2);
						pos_doc = arg2;
						Toast.makeText(base, "hell", 3000).show();
						Toast.makeText(base, ""+pos_doc, 3000).show();
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						Toast.makeText(base, "hellooo", 3000).show();
						
					}
				});*/
		return view;
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
			ob.put("doc_id", docList.get(pos_doc).getId());
			String response = HttpClient.SendHttpPost(Constants.FETCH_BOOKED_SLOT, ob.toString());
			
			if(response != null){
				slotarray.clear();
				JSONObject obj = new JSONObject(response);
				if(obj.getBoolean("status")){
					JSONArray arr = obj.getJSONArray("slots");
					myStringArray = new String[arr.length()];
					for(int i = 0;i<arr.length();i++){
						JSONObject object = arr.getJSONObject(i);
						myStringArray[i] = object.getString("slot");
					}
					for(int j = 1;j<=20;j++){
						int id = j;
						boolean status = false;
						for(int k = 0;k<myStringArray.length;k++){
							if(j == Integer.parseInt(myStringArray[k])){
								status = true;
							}
						}
						slotarray.add(new SlotBean(id, status));
					}
				}else{
					for(int m = 1;m<= 20;m++){
						slotarray.add(new SlotBean(m, false));
						
						System.out.println("reach here");
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
				adapter = new SlotAdapter(base, R.layout.grid_row, slotarray,list_date.get(pos_date),docList.get(pos_doc).getId());
				grid.setAdapter(adapter);
				ll_container.setVisibility(View.VISIBLE);
			}
		});
		
	}
	
	private void getAllstuffList() {
		try {
			base.doShowLoading();
			String response = HttpClient.SendHttpPost(Constants.FETCH_ALL_DOCTOR, "");
			if(response != null){
				JSONObject jsonres = new JSONObject(response);
				if(jsonres.getBoolean("status")){
					JSONArray jArr = jsonres.getJSONArray("doctorArray");
					for(int i=0; i<jArr.length(); i++){
						JSONObject c = jArr.getJSONObject(i);
						String id = c.getString("id");
						String fname = c.getString("fname");
						String lname = c.getString("lname");
						String username = c.getString("username");
						String specialization = c.getString("specialization");
						String degree = c.getString("degree");
						docList.add(new DoctorBean(id, fname, lname, username, specialization, degree));
						
					}
					
				}
				
				updateDocUi();
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	private void updateDocUi() {	
		base.runOnUiThread(new Runnable() {			
			@Override
			public void run() {
				base.doRemoveLoading();
				for(int i=0; i<docList.size(); i++){
					list_dr.add(docList.get(i).getFirstName()+" "+docList.get(i).getLastName());
				}
				ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(base,
						android.R.layout.simple_spinner_item, list_dr);
				dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					spinner_doc.setAdapter(dataAdapter1);
					spinner_doc.setSelected(true);
					spinner_doc.setSelection(0);
					spinner_doc.setOnItemSelectedListener(new OnItemSelectedListener() {

						@Override
						public void onItemSelected(AdapterView<?> arg0, View arg1,
								int arg2, long arg3) {
							spinner_doc.setSelection(arg2);
							pos_doc = arg2;
							//Toast.makeText(base, "hell", 3000).show();
							//Toast.makeText(base, ""+pos_doc, 3000).show();
						}

						@Override
						public void onNothingSelected(AdapterView<?> arg0) {
							//Toast.makeText(base, "hellooo", 3000).show();
							
						}
					});
			}
		});
		
	}
}
