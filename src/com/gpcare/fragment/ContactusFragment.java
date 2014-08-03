package com.gpcare.fragment;

import com.gpcare.model.Mailus;
import com.gpcare.model.Reach_us;
import com.gpcare.model.TalktoUs;
import com.gpcare.screen.BaseScreen;
import com.gpcare.screen.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class ContactusFragment extends Fragment implements OnClickListener{
	public BaseScreen base;
	public LinearLayout ll_reach_us,ll_talk_tous,ll_mail_us;
	public LinearLayout ll_contact_container;
	
	
	public ContactusFragment(BaseScreen b){
		base = b;
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment_contactus, container, false);
		ll_reach_us = (LinearLayout)view.findViewById(R.id.ll_reach_us);
		ll_reach_us.setOnClickListener(this);
		
		ll_talk_tous = (LinearLayout)view.findViewById(R.id.ll_talkto_us);
		ll_talk_tous.setOnClickListener(this);
		
		ll_mail_us = (LinearLayout)view.findViewById(R.id.ll_mail_us);
		ll_mail_us.setOnClickListener(this);
		
		ll_contact_container = (LinearLayout)view.findViewById(R.id.ll_contact_container);
		ll_contact_container.removeAllViews();
		ll_contact_container.addView(new Reach_us(base).mView);
		
		return view;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ll_reach_us:
			ll_contact_container.removeAllViews();
			ll_contact_container.addView(new Reach_us(base).mView);
			break;

		case R.id.ll_talkto_us:
			ll_contact_container.removeAllViews();
			ll_contact_container.addView(new TalktoUs(base).mView);
			break;
		case R.id.ll_mail_us:
			ll_contact_container.removeAllViews();
			ll_contact_container.addView(new Mailus(base).mView);
			break;
		
		}
	}
	
	

}
