package com.gpcare.fragment;

import com.gpcare.screen.BaseScreen;
import com.gpcare.screen.R;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class StaffFragment extends Fragment{
	public BaseScreen base;
	@SuppressLint("ValidFragment")
	public StaffFragment(BaseScreen b){
		base = b;
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment_stuff, container, false);
		return view;
	}
	
	

}
