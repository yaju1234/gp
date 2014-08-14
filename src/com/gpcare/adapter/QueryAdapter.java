package com.gpcare.adapter;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.gpcare.bean.QueryBean;
import com.gpcare.constants.Constants;
import com.gpcare.network.HttpClient;
import com.gpcare.screen.R;
import com.gpcare.settings.ImageLoader;

public class QueryAdapter extends ArrayAdapter<QueryBean>{
	
	private ProgressBar progressbar;
	private Activity activity;
	private ViewHolder mHolder;
	public ImageLoader imageLoader;
	public ArrayList<QueryBean> item = new ArrayList<QueryBean>();
	public boolean isClicked = false;
	
	public QueryAdapter(Activity activity,int textViewResourceId,ArrayList<QueryBean> items) {
		super(activity, textViewResourceId, items);
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
			v = vi.inflate(R.layout.query_row, null);
			mHolder = new ViewHolder();
			v.setTag(mHolder);
			
			mHolder.tv_fname = (TextView)v.findViewById(R.id.tv_fname);
			mHolder.tv_message = (TextView)v.findViewById(R.id.tv_message);		
			mHolder.btn_answer = (Button)v.findViewById(R.id.btn_answer);
		}
		else {
			mHolder =  (ViewHolder) v.getTag();
		}
		
		mHolder.btn_answer.setOnClickListener(new OnClickListener() {				
			@Override
			public void onClick(View v) {
				final Dialog dialog = new Dialog(activity);
				dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
				dialog.setContentView(R.layout.answer_dialog);
				dialog.setCancelable(true);
				
				final EditText ed_msg = (EditText)dialog.findViewById(R.id.ed_answer);
				Button btn_answer = (Button)dialog.findViewById(R.id.btn_answer);
				
				btn_answer.setOnClickListener(new OnClickListener() {					
					@Override
					public void onClick(View v) {
						if(!isClicked){
							if(ed_msg.getText().toString().trim().length()>0){
								sendAnswerToServer(ed_msg.getText().toString().trim(),item.get(position).getId(),position);
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
		
		final QueryBean mVendor = item.get(position);
		
		if(mVendor != null){
			mHolder.tv_fname.setText(mVendor.getName());
			mHolder.tv_message.setText(mVendor.getMessage());
		}	
		return v;
	}

	class ViewHolder {
		public TextView tv_fname,tv_message;
		public Button btn_answer;
	}
	
	private void sendAnswerToServer(final String msg, final String id, final int position) {	
		Thread t = new Thread(){
			public void run(){
				try {
					JSONObject obj = new JSONObject();
					obj.put("id",id);
					obj.put("answer",msg);
					String response = HttpClient.SendHttpPost(Constants.ANSER_TO_QUERY, obj.toString());
					if(response!=null){
						JSONObject ob = new JSONObject(response);
						if(ob.getBoolean("status")){
							item.remove(position);							
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
				Toast.makeText(activity, "Answer successfully submitted", 5000).show();
				notifyDataSetChanged();
			}
		});
	}
}
