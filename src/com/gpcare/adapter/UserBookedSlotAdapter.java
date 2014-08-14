package com.gpcare.adapter;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gpcare.bean.UserBookedSlotBean;
import com.gpcare.constants.Constants;
import com.gpcare.network.HttpClient;
import com.gpcare.screen.BaseScreen;
import com.gpcare.screen.R;
import com.gpcare.settings.ImageLoader;

public class UserBookedSlotAdapter extends ArrayAdapter<UserBookedSlotBean>{
	
	private BaseScreen activity;
	private ViewHolder mHolder;
	public ImageLoader imageLoader;
	public ArrayList<UserBookedSlotBean> item = new ArrayList<UserBookedSlotBean>();
	public String date,doctor_id;
	public boolean isClicked = false;
	
	public setHomeFragmentListener listener; 
	
	public interface setHomeFragmentListener{
		public void loadHomeFragment();
	}
	
	public UserBookedSlotAdapter(BaseScreen activity,int textViewResourceId,ArrayList<UserBookedSlotBean> items) {
		super(activity,textViewResourceId, items);
		this.item = items;
		this.activity = activity;
	}

	@Override
	public int getCount() {
		return item.size();
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView( final int position,  View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {
			LayoutInflater vi = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.show_booked_slot, null);

			mHolder = new ViewHolder();
			v.setTag(mHolder);
			
			mHolder.appo_no = (TextView)v.findViewById(R.id.tv_app_no);
			mHolder.date = (TextView)v.findViewById(R.id.tv_date);
			mHolder.time = (TextView)v.findViewById(R.id.tv_time);
			mHolder.details = (TextView)v.findViewById(R.id.tv_details);
			mHolder.btn_edit = (Button)v.findViewById(R.id.btn_edit);
		}
		else {
			mHolder =  (ViewHolder) v.getTag();
		}			
		
		final UserBookedSlotBean mVendor = item.get(position);
		
		mHolder.btn_edit.setOnClickListener(new OnClickListener() {				
			@Override
			public void onClick(View v) {
				final Dialog dialog = new Dialog(activity);
				dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
				dialog.setContentView(R.layout.edit_booked_slot_dialog);
				dialog.setCancelable(true);
				
				final EditText ed_msg = (EditText)dialog.findViewById(R.id.ed_answer);
				ed_msg.setText(item.get(position).getProblem());
				Button btn_answer = (Button)dialog.findViewById(R.id.btn_edit);
				
				btn_answer.setOnClickListener(new OnClickListener() {					
					@Override
					public void onClick(View v) {
						if(!isClicked){
							if(ed_msg.getText().toString().trim().length()>0){
								sendAnswerToServer(ed_msg.getText().toString().trim(),item.get(position).getAppo_no(),position);
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

		if(mVendor != null){

			mHolder.appo_no.setText(mVendor.getAppo_no());
			mHolder.date.setText(mVendor.getDate());
			mHolder.time.setText(fun(Integer.parseInt(mVendor.getTime())));
			mHolder.details.setText(mVendor.getProblem());
			
		}		
		return v;
	}

	class ViewHolder {
		public TextView appo_no,date,time,details;
		public Button btn_edit;
	}
	
	private void sendAnswerToServer(final String msg, final String id, final int position) {	
		Thread t = new Thread(){
			public void run(){
				try {
					JSONObject obj = new JSONObject();
					obj.put("id",id);
					obj.put("user_id",activity.app.getUserinfo().user_id);
					obj.put("details",msg);
					String response = HttpClient.SendHttpPost(Constants.UPDATE_PROBLEM_DETAILS, obj.toString());
					if(response!=null){
						JSONObject ob = new JSONObject(response);
						if(ob.getBoolean("status")){
							item.get(position).setProblem(msg);		
							
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
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				notifyDataSetChanged();
				Toast.makeText(activity, "Problem details successfully updated", 5000).show();
			}
		});
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
