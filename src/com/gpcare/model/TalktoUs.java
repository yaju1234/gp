package com.gpcare.model;

import android.view.View;
import android.widget.TextView;

import com.gpcare.screen.BaseScreen;
import com.gpcare.screen.R;

public class TalktoUs {
	
	public BaseScreen base;
	public View mView = null;
	public TextView tv_address,tv_contact,tv_email,tv_fax,tv_website_info;
	
	public TalktoUs(BaseScreen b){
		base = b;
		mView = View.inflate(base, R.layout.talkto_us, null);
		
		tv_address = (TextView)mView.findViewById(R.id.tv_address);
		tv_contact = (TextView)mView.findViewById(R.id.tv_contact);
		tv_fax = (TextView)mView.findViewById(R.id.tv_fax);
		tv_website_info = (TextView)mView.findViewById(R.id.tv_website_info);
	}
}
