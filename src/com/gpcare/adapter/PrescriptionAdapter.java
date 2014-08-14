package com.gpcare.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.gpcare.bean.PrescriptionBean;
import com.gpcare.screen.BaseScreen;
import com.gpcare.screen.R;
import com.gpcare.settings.ImageLoader;

public class PrescriptionAdapter extends ArrayAdapter<PrescriptionBean>{
	
	private BaseScreen activity;
	private ViewHolder mHolder;
	public ImageLoader imageLoader;
	public ArrayList<PrescriptionBean> item = new ArrayList<PrescriptionBean>();
	public String date,doctor_id;
	
	public PrescriptionAdapter(BaseScreen activity,int textViewResourceId,ArrayList<PrescriptionBean> items) {
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
			v = vi.inflate(R.layout.repeat_prescription_row, null);

			mHolder = new ViewHolder();
			v.setTag(mHolder);
			
			mHolder.tv_item = (TextView)v.findViewById(R.id.tv_item);
			mHolder.tv_qty = (TextView)v.findViewById(R.id.tv_qty);	
			mHolder.tv_order_date = (TextView)v.findViewById(R.id.tv_order_date);
			mHolder.tv_delevary_date = (TextView)v.findViewById(R.id.tv_delevary_date);	
		}
		else {
			mHolder =  (ViewHolder) v.getTag();
		}			
		
		final PrescriptionBean mVendor = item.get(position);

		if(mVendor != null){
			
				mHolder.tv_item.setText(mVendor.getItem_ordered());
				mHolder.tv_qty.setText(mVendor.getQuantity_ordered());
			
				mHolder.tv_order_date.setText(mVendor.getDate_ordered());
				mHolder.tv_delevary_date.setText(mVendor.getEstimate_delivery_date());;
		}		
		return v;
	}

	class ViewHolder {
		public TextView tv_item,tv_qty,tv_order_date,tv_delevary_date;
	}
}
