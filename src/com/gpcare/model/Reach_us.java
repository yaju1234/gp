package com.gpcare.model;

import android.view.View;
import android.widget.TextView;

import com.gpcare.screen.BaseScreen;
import com.gpcare.screen.R;

public class Reach_us {

	public BaseScreen base;
	public View mView = null;
	public TextView tv_monday,tv_tuesday,tv_wednesday,tv_thursday,tv_friday,tv_saturday;
	
	public Reach_us(BaseScreen b){
		base = b;
		mView = View.inflate(base, R.layout.reach_us, null);
		tv_monday = (TextView)mView.findViewById(R.id.tv_monday);
		tv_tuesday = (TextView)mView.findViewById(R.id.tv_tuesday);
		tv_wednesday = (TextView)mView.findViewById(R.id.tv_wednesday);
		tv_thursday = (TextView)mView.findViewById(R.id.tv_thursday);
		tv_friday = (TextView)mView.findViewById(R.id.tv_friday);
		tv_saturday = (TextView)mView.findViewById(R.id.tv_saturday);
	}
}
