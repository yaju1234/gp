package com.gpcare.fragment.admin;

import com.gpcare.screen.BaseScreen;
import com.gpcare.screen.R;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

@SuppressLint("ValidFragment")
public class AdminUpdateMessageBoardFragment extends Fragment implements OnClickListener{
	
	private BaseScreen base;
	private Button btn_submit1,btn_submit2,btn_submit3;
	private EditText et_text1,et_text2,et_text3;
	
	public AdminUpdateMessageBoardFragment(BaseScreen b){
		base = b;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.admin_message_board, container, false);
		btn_submit1 = (Button)view.findViewById(R.id.btn_submit1);
		btn_submit2 = (Button)view.findViewById(R.id.btn_submit2);
		btn_submit3 = (Button)view.findViewById(R.id.btn_submit3);
		et_text1 = (EditText)view.findViewById(R.id.et_text1);
		et_text2 = (EditText)view.findViewById(R.id.et_text2);
		et_text3 = (EditText)view.findViewById(R.id.et_text3);
		btn_submit1.setOnClickListener(this);
		btn_submit2.setOnClickListener(this);
		btn_submit3.setOnClickListener(this);
		return view;
	}


	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_submit1:
			
			break;
		case R.id.btn_submit2:
			
			break;

		case R.id.btn_submit3:
	
	break;


		}
		
	}

}
