package com.gpcare.model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Dialog;
import android.content.ClipData.Item;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.gpcare.adapter.UserBookedSlotAdapter;
import com.gpcare.bean.PrescriptionBean;
import com.gpcare.bean.UserBookedSlotBean;
import com.gpcare.constants.Constants;
import com.gpcare.fragment.HomeFragment;
import com.gpcare.model.UserPrescription.UserPrescritionBackListener;
import com.gpcare.network.HttpClient;
import com.gpcare.screen.BaseScreen;
import com.gpcare.screen.R;

public class UserBookedSlot implements OnClickListener{

	public interface UserbBookedSlotBackListener {
		public void onBookedSlotDoneClick();
	}
	
	public BaseScreen base;
	public View mviView;
	public LinearLayout ll_list;
	public Button btn_submit,btn_done;
	public ArrayList<UserBookedSlotBean> list = new ArrayList<UserBookedSlotBean>();
	public UserBookedSlotAdapter adapter;
	
	private View mview;
	public boolean isClicked = false;
	
	private UserbBookedSlotBackListener listener;
	
	public UserBookedSlot(BaseScreen b, HomeFragment fragment){
		this.base = b;
		mviView = View.inflate(base, R.layout.user_all_booked_appoinment1, null);
		ll_list = (LinearLayout)mviView.findViewById(R.id.ll_booked_list);
		
		btn_done = (Button)mviView.findViewById(R.id.btn_done);
		btn_done.setOnClickListener(this);
		listener = (UserbBookedSlotBackListener) fragment;
		getAllBookedList();
	}

	private void getAllBookedList() {
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
			ob.put("id", base.app.getUserinfo().user_id);
			String response = HttpClient.SendHttpPost(Constants.GET_BOOKED_SLOT, ob.toString());
			if(response != null){
				
				System.out.println("##--reach here"+response);
				list.clear();
				JSONObject obj = new JSONObject(response);
				JSONArray arr = obj.getJSONArray("slotsArray");
				for(int i = 0;i<arr.length();i++){
					JSONObject object = arr.getJSONObject(i);
					list.add(new UserBookedSlotBean(object.getString("appointment_no"),
							object.getString("slot"),
							object.getString("date"),
							object.getString("doctor_name"),
							object.getString("details")));
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
				//adapter = new UserBookedSlotAdapter(base, R.layout.show_booked_slot, list);
				//ll_list.setAdapter(adapter);
				ll_list.removeAllViews();
				for(int j = 0;j<list.size();j++){
					final int k = j;
					mview = LayoutInflater.from(base).inflate(R.layout.show_booked_slot, null);
					
					TextView appo_no = (TextView)mview.findViewById(R.id.tv_app_no);
					TextView date = (TextView)mview.findViewById(R.id.tv_date);
					TextView time = (TextView)mview.findViewById(R.id.tv_time);
					TextView details = (TextView)mview.findViewById(R.id.tv_details);
					Button btn_edit = (Button)mview.findViewById(R.id.btn_edit);
					
					appo_no.setText(list.get(j).getAppo_no());
					date.setText(list.get(j).getDate());
					time.setText(fun(Integer.parseInt(list.get(j).getTime())));
					details.setText(list.get(j).getProblem());
					
					btn_edit.setOnClickListener(new OnClickListener() {				
						@Override
						public void onClick(View v) {
							final Dialog dialog = new Dialog(base);
							dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
							dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
							dialog.setContentView(R.layout.edit_booked_slot_dialog);
							dialog.setCancelable(true);
							
							final EditText ed_msg = (EditText)dialog.findViewById(R.id.ed_answer);
							ed_msg.setText(list.get(k).getProblem());
							Button btn_answer = (Button)dialog.findViewById(R.id.btn_edit);
							
							btn_answer.setOnClickListener(new OnClickListener() {					
								@Override
								public void onClick(View v) {
									if(!isClicked){
										if(ed_msg.getText().toString().trim().length()>0){
											sendAnswerToServer(ed_msg.getText().toString().trim(),list.get(k).getAppo_no(),k);
											dialog.cancel();
										}else{
											ed_msg.setError("Please enter your message");
										}
									}
								}
							});
							dialog.show();
						}
					});	
					ll_list.addView(mview);
				}
			}
		});
	}
	
	private void sendAnswerToServer(final String msg, final String id, final int position) {	
		Thread t = new Thread(){
			public void run(){
				try {
					JSONObject obj = new JSONObject();
					obj.put("id",id);
					obj.put("user_id",base.app.getUserinfo().user_id);
					obj.put("details",msg);
					String response = HttpClient.SendHttpPost(Constants.UPDATE_PROBLEM_DETAILS, obj.toString());
					if(response!=null){
						JSONObject ob = new JSONObject(response);
						if(ob.getBoolean("status")){
							ShowSuccessMessage();
						}
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}				
			}
		};
		t.start();		
	}
	private void ShowSuccessMessage() {
		base.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				getAllBookedList();
				Toast.makeText(base, "Problem details successfully updated", 5000).show();
			}
		});
	}
	
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_done:
			listener.onBookedSlotDoneClick();
		}
	}
	
	public String  fun(int id){
		String st = "";
		if(id == 1){
			st = "9.00 am - 9.15 am"; 
		}else if(id == 2){
			st = "9.15 am - 9.30 am"; 
		}else if(id == 3){
			st = "9.30 am - 9.45 am"; 
		}else if(id == 4){
			st = "9.45 am - 10.00 am"; 
		}else if(id == 5){
			st = "10.00 am - 10.15 am"; 
		}else if(id == 6){
			st = "10.15 am - 10.30 am"; 
		}else if(id == 7){
			st = "10.30 am - 10.45 am"; 
		}else if(id == 8){
			st = "10.45 am - 11.00 am"; 
		}else if(id == 9){
			st = "11.00 am - 11.15 am"; 
		}else if(id == 10){
			st = "11.15 am - 11.30 am"; 
		}else if(id == 11){
			st = "11.30 am - 11.45 am"; 
		}else if(id == 12){
			st = "11.45 am - 12.00 pm"; 
		}else if(id == 13){
			st = "12.00 pm - 12.15 pm"; 
		}else if(id == 14){
			st = "12.15 pm - 12.30 pm"; 
		}else if(id == 15){
			st = "12.30 pm - 12.45 pm"; 
		}else if(id == 16){
			st = "12.45 pm - 01.00 pm"; 
		}else if(id == 17){
			st = "01.00 pm - 01.15 pm"; 
		}else if(id == 18){
			st = "01.15 pm - 01.30 pm"; 
		}else if(id == 19){
			st = "01.30 pm - 01.45 pm"; 
		}else if(id == 20){
			st = "01.45 pm - 02.00 pm"; 
		}
		return st;
	}
}
