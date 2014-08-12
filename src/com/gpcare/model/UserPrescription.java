package com.gpcare.model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gpcare.adapter.PrescriptionAdapter;
import com.gpcare.bean.PrescriptionBean;
import com.gpcare.constants.Constants;
import com.gpcare.fragment.HomeFragment;
import com.gpcare.network.HttpClient;
import com.gpcare.screen.BaseScreen;
import com.gpcare.screen.R;

public class UserPrescription implements OnClickListener{
	
	public interface UserPrescritionBackListener {
		public void onPrescritionDoneClick();
	}
	
	public BaseScreen base;
	public View mviView;
	public LinearLayout ll_order_list,ll_add_view;
	public CheckBox cb_home,cb_gpcare;
	public EditText ed_gp_number,ed_medicine,ed_qty;
	public Button btn_submit,btn_done;
	String gp_reg_no,medicine_name,qty;
	private HomeFragment fragment;
	
	private View mview;
	
	private PrescriptionAdapter adapter;
	
	private ArrayList<PrescriptionBean> list = new ArrayList<PrescriptionBean>();
	
	private UserPrescritionBackListener listener;
	
	private boolean isClicked = false;
	
	public UserPrescription(BaseScreen b,HomeFragment fragment){
		this.base = b;
		mviView = View.inflate(base, R.layout.user_repeat_prescription, null);
		listener = (UserPrescritionBackListener) fragment;
		
		this.fragment = fragment;
		ll_order_list = (LinearLayout)mviView.findViewById(R.id.ll_order_list);
		ll_add_view = (LinearLayout)mviView.findViewById(R.id.ll_add_view);
		
		btn_submit = (Button)mviView.findViewById(R.id.btn_submit);
		btn_submit.setOnClickListener(this);
		
		btn_done = (Button)mviView.findViewById(R.id.btn_done);
		btn_done.setOnClickListener(this);
		
		ed_gp_number = (EditText)mviView.findViewById(R.id.ed_gp_no);
		ed_medicine = (EditText)mviView.findViewById(R.id.ed_medicine_name);
		ed_qty = (EditText)mviView.findViewById(R.id.ed_qty);
		
		cb_home = (CheckBox)mviView.findViewById(R.id.cb_home);
		cb_home.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked){
					cb_gpcare.setChecked(false);
				}
			}
		});
		
		cb_gpcare = (CheckBox)mviView.findViewById(R.id.cb_gp_care);
		
		cb_gpcare.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked){
					cb_home.setChecked(false);
				}
			}
		});
		
		
		if(cb_home.isChecked()){
			cb_gpcare.setChecked(false);
		}else{
			cb_home.setChecked(true);
		}
		
		getorderedPrescription();
	}
	
	private void getorderedPrescription() {
		Thread t = new Thread(){
			public void run(){
				callwebservicetofetch();
			}			
		};
		t.start();
	}

	private void callwebservicetofetch() {
		JSONObject ob = new JSONObject();
		try {
			ob.put("user_id", base.app.getUserinfo().user_id);
			String response = HttpClient.SendHttpPost(Constants.FETCH_PRESCRIPTION, ob.toString());
			if(response != null){
				list.clear();
				JSONObject obj = new JSONObject(response);
				JSONArray arr = obj.getJSONArray("prescription_list");
				for(int i = 0;i<arr.length();i++){
					JSONObject object = arr.getJSONObject(i);
					list.add(new PrescriptionBean(object.getString("item_name"),
							object.getString("qty_ordered"),
							object.getString("date_ordered"), 
							object.getString("delivery_date")));
				}
				populateList();
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	private void populateList() {
		base.runOnUiThread(new Runnable() {			
			@Override
			public void run() {
				ll_add_view.removeAllViews();
				for(int j = 0;j<list.size();j++){
					mview = LayoutInflater.from(base).inflate(R.layout.repeat_prescription_row, null);
					TextView tv_item = (TextView)mview.findViewById(R.id.tv_item);
					TextView tv_qty = (TextView)mview.findViewById(R.id.tv_qty);	
					TextView tv_order_date = (TextView)mview.findViewById(R.id.tv_order_date);
					TextView tv_delevary_date = (TextView)mview.findViewById(R.id.tv_delevary_date);	
					
					tv_item.setText(list.get(j).getItem_ordered());
					tv_qty.setText(list.get(j).getQuantity_ordered());
				
					tv_order_date.setText(list.get(j).getDate_ordered());
					tv_delevary_date.setText(list.get(j).getEstimate_delivery_date());
					
					ll_add_view.addView(mview);
				}
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_submit:
			if(!isClicked){
				isClicked = true;
				if(isValid()){
					doUpdateProfile();
				}
			}
			break;		
		case R.id.btn_done:
			listener.onPrescritionDoneClick();
		}
	}
	private boolean isValid(){
		boolean flag = true;
		gp_reg_no = ed_gp_number.getText().toString().trim();
		medicine_name = ed_medicine.getText().toString().trim();
		qty = ed_qty.getText().toString().trim();
		
		if(gp_reg_no.length() == 0){
			ed_gp_number.setError("Please enter your location");
			isClicked = false;
			flag = false;
		}else if(medicine_name.length() == 0){
			ed_medicine.setError("Please enter your email");;
			isClicked = false;
			flag = false;
		}else if(qty.length() == 0){
			ed_qty.setError("Please enter your contact");;
			flag = false;
			isClicked = false;
			
		}else if(!(cb_gpcare.isChecked() || cb_home.isChecked())){
			flag = false;
			isClicked = false;
			base.runOnUiThread(new Runnable() {				
				@Override
				public void run() {
					Toast.makeText(base, "Please select one shipping address", Toast.LENGTH_SHORT).show();
				}
			});
		}
		return flag;
	}
	private void doUpdateProfile() {
		Thread t = new Thread(){
			public void run(){
				base.doShowLoading();
				callServerForUpdate();
				base.doRemoveLoading();
			}
		};
		t.start();
	}
	private void callServerForUpdate() {
		
		try {
			JSONObject object = new JSONObject();
			object.put("user_id", base.app.getUserinfo().user_id);	
			object.put("medicine_name", medicine_name);		
			object.put("gp_reg_no",gp_reg_no );
			object.put("qty", qty);		
			if(cb_home.isChecked()){
				object.put("shipping_address", "home");	
			}else if(cb_home.isChecked()){
				object.put("shipping_address", "gp_care");
			}
			
			String response = HttpClient.SendHttpPost(Constants.USER_PRESCRIPTION, object.toString());
			if(response != null){
				JSONObject ob = new JSONObject(response);
				if(ob.getBoolean("status")){
					
					updateUi();
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	private void updateUi() {
		base.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				Toast.makeText(base, "Your profile has successfully Updated ", Toast.LENGTH_SHORT).show();
				getorderedPrescription();
			}
		});
		
	}
}
