package com.gpcare.fragment;

import com.gpcare.screen.BaseScreen;
import com.gpcare.screen.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AppointmentFragment extends Fragment{
	public BaseScreen base;
	public AppointmentFragment(BaseScreen b){
		base = b;
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment_appoinment, container, false);
		return view;
	}
}