package com.gpcare.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.gpcare.bean.DocSlotBean;
import com.gpcare.screen.BaseScreen;
import com.gpcare.screen.R;
import com.gpcare.settings.ImageLoader;

public class DocAvailAdapter extends ArrayAdapter<DocSlotBean>{
	
	private BaseScreen activity;
	private ViewHolder mHolder;
	public ImageLoader imageLoader;
	public ArrayList<DocSlotBean> item = new ArrayList<DocSlotBean>();
	
	public setHomeFragmentListener listener; 
	
	public interface setHomeFragmentListener{
		public void loadHomeFragment();
	}
	
	public DocAvailAdapter(BaseScreen activity,int textViewResourceId,ArrayList<DocSlotBean> items) {
		super(activity,textViewResourceId, items);
		this.item = items;
		this.activity = activity;
		
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
			v = vi.inflate(R.layout.doc_avail_row, null);

			mHolder = new ViewHolder();
			v.setTag(mHolder);
			
			mHolder.name = (TextView)v.findViewById(R.id.tv_name);
			mHolder.date = (TextView)v.findViewById(R.id.tv_date);
			mHolder.desc = (TextView)v.findViewById(R.id.tv_desc);
			
		}
		else {
			mHolder =  (ViewHolder) v.getTag();
		}			
		
		final DocSlotBean mVendor = item.get(position);
		
		if(mVendor != null){
			
				mHolder.name.setText(mVendor.getName());
				mHolder.date.setText(fun(Integer.parseInt(mVendor.getTime())));
				mHolder.desc.setText(mVendor.getDesc());
				
		}		
		return v;
	}

	class ViewHolder {
		public TextView name;
		public TextView date;
		public TextView desc;
		
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
