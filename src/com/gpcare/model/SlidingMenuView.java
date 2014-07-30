package com.gpcare.model;

import com.gpcare.screen.BaseScreen;
import com.gpcare.screen.R;

import android.view.View;

public class SlidingMenuView {
	
	public View mView = null;
	public BaseScreen base = null;
	
	public SlidingMenuView(BaseScreen b){
		base = b;
		
		mView = View.inflate(base, R.layout.sliding_menu, null);
	}

}
