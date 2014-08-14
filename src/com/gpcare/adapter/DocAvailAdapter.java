package com.gpcare.adapter;

import android.view.View;
import android.widget.TextView;
import com.gpcare.screen.BaseScreen;
import com.gpcare.screen.R;
import com.gpcare.settings.ImageLoader;

public class DocAvailAdapter {
	
	private BaseScreen base;
	public ImageLoader imageLoader;
	public View v;
	public TextView name;
	public TextView date;
	public TextView desc;

	
	public DocAvailAdapter(BaseScreen b, String name1, String slot1, String desc1) {
		this.base = b;
		v= View.inflate(base, R.layout.doc_avail_row, null);
		name = (TextView)v.findViewById(R.id.tv_name);
		date = (TextView)v.findViewById(R.id.tv_date);
		desc = (TextView)v.findViewById(R.id.tv_desc);
		name.setText(name1);
		date.setText(fun(Integer.parseInt(slot1)));
		desc.setText(desc1);
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
