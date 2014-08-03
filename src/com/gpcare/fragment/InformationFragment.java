package com.gpcare.fragment;

import com.gpcare.screen.BaseScreen;
import com.gpcare.screen.R;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class InformationFragment extends Fragment{
	public BaseScreen base;
	private TextView tv_gov,tv_gp,tv_lc;
	
	@SuppressLint("ValidFragment")
	public InformationFragment(BaseScreen b){
		base = b;
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment_infozone, container, false);
		
		tv_gov = (TextView)view.findViewById(R.id.tv_gov);
		tv_gp = (TextView)view.findViewById(R.id.tv_gp);
		tv_lc = (TextView)view.findViewById(R.id.tv_lc);
			
		return view;
	}
}
