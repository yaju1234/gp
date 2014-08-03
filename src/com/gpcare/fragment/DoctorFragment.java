package com.gpcare.fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.gpcare.screen.BaseScreen;
import com.gpcare.screen.R;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

@SuppressLint("ValidFragment") public class DoctorFragment extends Fragment{
	public BaseScreen base;
	private Spinner spinner_date,spinner_doc;
	private Calendar cal;
	private List<String> list_date = new ArrayList<String>();
	private List<String> list_dr = new ArrayList<String>();
	private String doc[] = {"Dr. A","Dr. B","Dr. C","Dr. D","Dr. E"};
	@SuppressLint("SimpleDateFormat") public DoctorFragment(BaseScreen b){
		base = b;
		cal = Calendar.getInstance();
		for(int i= 0; i<30; i++){
			cal.add(Calendar.DATE, +i);
			list_date.add(new SimpleDateFormat( "dd-MM-yyyy").format(cal.getTime()));
		}
		for(int i=0; i<doc.length; i++){
			list_dr.add(doc[i]);
		}
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment_doc_availabilty, container, false);
		spinner_date = (Spinner)view.findViewById(R.id.spinner_date);
		spinner_doc = (Spinner)view.findViewById(R.id.spinner_doctor);
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(base,
				android.R.layout.simple_spinner_item, list_date);
			dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spinner_date.setAdapter(dataAdapter);
			spinner_date.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					
					spinner_date.setSelection(arg2);
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					
					
				}
			});
			
			ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(base,
					android.R.layout.simple_spinner_item, list_dr);
			dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				spinner_date.setAdapter(dataAdapter1);
				spinner_date.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						
						spinner_doc.setSelection(arg2);
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						
						
					}
				});
		return view;
	}
	
	

}
