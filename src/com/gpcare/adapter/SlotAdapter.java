package com.gpcare.adapter;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.R.color;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gpcare.bean.SlotBean;
import com.gpcare.constants.Constants;
import com.gpcare.network.HttpClient;
import com.gpcare.screen.BaseScreen;
import com.gpcare.screen.R;
import com.gpcare.settings.ImageLoader;

public class SlotAdapter extends ArrayAdapter<SlotBean>{
	
	private BaseScreen activity;
	private ViewHolder mHolder;
	public ImageLoader imageLoader;
	public ArrayList<SlotBean> item = new ArrayList<SlotBean>();
	public String date,doctor_id;
	
	public setHomeFragmentListener listener; 
	
	public interface setHomeFragmentListener{
		public void loadHomeFragment();
	}
	
	public SlotAdapter(BaseScreen activity,int textViewResourceId,ArrayList<SlotBean> items, String date, String doc_id) {
		super(activity,textViewResourceId, items);
		this.item = items;
		this.activity = activity;
		this.date = date;
		this.doctor_id = doc_id;
		
		listener = (setHomeFragmentListener) activity;
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
			v = vi.inflate(R.layout.grid_row, null);

			mHolder = new ViewHolder();
			v.setTag(mHolder);
			
			mHolder.mName = (TextView)v.findViewById(R.id.tv_status);
			mHolder.ll_row = (LinearLayout)v.findViewById(R.id.ll_row);	
		}
		else {
			mHolder =  (ViewHolder) v.getTag();
		}			
		
		final SlotBean mVendor = item.get(position);
		
		mHolder.ll_row.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(!item.get(position).getStatus()){
					System.out.println("##--session11"+activity.app.getUserinfo().session);
					System.out.println("##--session11"+activity.app.getDoctorinfo().session);
					/*if(!activity.app.getDoctorinfo().session){*/
					if(activity.app.getUserinfo().session && (!item.get(position).getStatus())){
						AlertDialog.Builder builder = new AlertDialog.Builder(activity);
				        builder.setCancelable(true);
				        builder.setTitle("Book Your Slot");
				        builder.setInverseBackgroundForced(true);
				        builder.setPositiveButton("Book",
				                new DialogInterface.OnClickListener() {
				                    @Override
				                    public void onClick(DialogInterface dialog,int which) {
				                    	if(activity.app.getUserinfo().session){
				                    		final Dialog dialog1 = new Dialog(activity);
				            				dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
				            				dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
				            				dialog1.setContentView(R.layout.problem_detail_dialog);
				            				dialog1.setCancelable(true);
				            				
				            				final EditText ed_msg = (EditText)dialog1.findViewById(R.id.ed_answer);
				            				Button btn_answer = (Button)dialog1.findViewById(R.id.btn_answer);
				            				
				            				btn_answer.setOnClickListener(new OnClickListener() {					
				            					@Override
				            					public void onClick(View v) {
				            						if(ed_msg.getText().toString().trim().length()>0){
				            							sendBookRequest(position,ed_msg.getText().toString().trim());
				            							dialog1.cancel();
				            						}else{
				            							ed_msg.setError("Please enter your Problem Details");
				            						}
				            					}
				            				});
				            				dialog1.show();		                    		
				                    	}else{
				                    		Toast.makeText(activity, "Please login to book your slot", 10000).show();
				                    		listener.loadHomeFragment();
				                    	}
				                    }
				                });
				        builder.setNegativeButton("Cancel",
				                new DialogInterface.OnClickListener() {
				                    @Override
				                    public void onClick(DialogInterface dialog,int which) {
				                        dialog.dismiss();
				                      
				                    }
				                });
				        AlertDialog alert = builder.create();
				        alert.show();
					}
				}else if(activity.app.getAdmininfo().session && item.get(position).getStatus()){

					AlertDialog.Builder builder = new AlertDialog.Builder(activity);
			        builder.setCancelable(true);
			        builder.setTitle("Delete Slot");
			        builder.setInverseBackgroundForced(true);
			        builder.setPositiveButton("Delete",
			                new DialogInterface.OnClickListener() {
			                    @Override
			                    public void onClick(DialogInterface dialog,int which) {
			                    	sendBookcancelRequest(position);
			                    		/*final Dialog dialog1 = new Dialog(activity);
			            				dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
			            				dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
			            				dialog1.setContentView(R.layout.problem_detail_dialog);
			            				dialog1.setCancelable(true);
			            				
			            				final EditText ed_msg = (EditText)dialog1.findViewById(R.id.ed_answer);
			            				Button btn_answer = (Button)dialog1.findViewById(R.id.btn_answer);
			            				
			            				btn_answer.setOnClickListener(new OnClickListener() {					
			            					@Override
			            					public void onClick(View v) {
			            						if(ed_msg.getText().toString().trim().length()>0){
			            							sendBookRequest(position,ed_msg.getText().toString().trim());
			            							dialog1.cancel();
			            						}else{
			            							ed_msg.setError("Please enter your Problem Details");
			            						}
			            					}
			            				});
			            				dialog1.show();	*/	                    		
			                    	
			                    }
			                });
			        builder.setNegativeButton("Cancel",
			                new DialogInterface.OnClickListener() {
			                    @Override
			                    public void onClick(DialogInterface dialog,int which) {
			                        dialog.dismiss();
			                      
			                    }
			                });
			        AlertDialog alert = builder.create();
			        alert.show();
				
				}	
			}
		});

		if(mVendor != null){
			if(mVendor.getStatus()){
				mHolder.mName.setText("Booked");
				mHolder.mName.setTextColor(Color.RED);
			}else{
				mHolder.mName.setText("Open");
				mHolder.mName.setTextColor(Color.GREEN);
			}
		}		
		return v;
	}

	class ViewHolder {
		public TextView mName;
		public LinearLayout ll_row;
	}
	
	private void sendBookRequest(final int position, final String msg) {	
		Thread t = new Thread(){
			public void run(){
				try {
					JSONObject obj = new JSONObject();
					obj.put("userid",activity.app.getUserinfo().user_id);
					obj.put("docid",doctor_id);
					obj.put("slot",""+(position+1));
					obj.put("details",msg);
					obj.put("date",date);
					String response = HttpClient.SendHttpPost(Constants.BOOKE_SLOT_REQ, obj.toString());
					if(response!=null){
						JSONObject ob = new JSONObject(response);
						if(ob.getBoolean("status")){						
							gotoNextScreen(position);
						}
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}				
			}	
		};
		t.start();		
	}
	private void sendBookcancelRequest(final int position) {	
		Thread t = new Thread(){
			public void run(){
				try {
					JSONObject obj = new JSONObject();
					obj.put("docid",doctor_id);
					obj.put("slot",""+(position+1));
					obj.put("date",date);
					String response = HttpClient.SendHttpPost(Constants.BOOKE_SLOT_REQ, obj.toString());
					if(response!=null){
						JSONObject ob = new JSONObject(response);
						if(ob.getBoolean("status")){						
							gotoNextScreen1(position);
						}
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}				
			}	
		};
		t.start();		
	}
	private void gotoNextScreen(final int pos) {
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				item.get(pos).setStatus(true);
				notifyDataSetChanged();
				Toast.makeText(activity, "You have successfully booked your appoinment", 5000).show();
			}
		});
	}
	
	private void gotoNextScreen1(final int pos) {
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				item.get(pos).setStatus(false);
				notifyDataSetChanged();
				Toast.makeText(activity, "You have successfully Cancled appoinment", 5000).show();
			}
		});
	}
}
