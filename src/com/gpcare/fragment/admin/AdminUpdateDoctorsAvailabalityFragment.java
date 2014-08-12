package com.gpcare.fragment.admin;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.gpcare.bean.StuffDoctorBean;
import com.gpcare.constants.Constants;
import com.gpcare.model.admin.DoctorAvalibility;
import com.gpcare.network.HttpClient;
import com.gpcare.screen.BaseScreen;
import com.gpcare.screen.R;
import com.gpcare.settings.ImageLoader;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("ValidFragment")
public class AdminUpdateDoctorsAvailabalityFragment extends Fragment{
	
	private BaseScreen base;
	public ArrayList<StuffDoctorBean> docList = new ArrayList<StuffDoctorBean>();
	
	private LinearLayout ll_doctors_container;
	private ImageLoader imageloader;
	private LinearLayout ll_doctors_profile;
	private Button btn_staff_profile;
	private LinearLayout ll_availanle_chart;
	private View vi;
	int height;
	int width ;
	private TextView textView1_;
	
	public AdminUpdateDoctorsAvailabalityFragment(BaseScreen b){
		base = b;
		WindowManager wm = (WindowManager) base.getSystemService(base.WINDOW_SERVICE);
		final DisplayMetrics displayMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(displayMetrics);
		 height = displayMetrics.heightPixels;
		 width = displayMetrics.widthPixels;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.update_fragment_doc_availability, container, false);
		ll_doctors_container = (LinearLayout)view.findViewById(R.id.ll_doctors_container);
		btn_staff_profile = (Button)view.findViewById(R.id.btn_staff_profile);
	
		ll_availanle_chart= (LinearLayout)view.findViewById(R.id.ll_availanle_chart);
		btn_staff_profile.setVisibility(View.VISIBLE);
		ll_availanle_chart.setVisibility(View.GONE);
		textView1_ = (TextView)view.findViewById(R.id.textView1_);
		imageloader = new ImageLoader(base);
		
		getstaffList();
		return view;
		
	}
	
	private void getstaffList() {
		Thread t = new Thread(){
			public void run(){
				getAllstuffList();
				
			}
		};
		t.start();
	}
	
	private void getAllstuffList() {
		try {
			base.doShowLoading();
			
			JSONObject obj = new JSONObject();
			obj.put("user_id", "23");
			String response = HttpClient.SendHttpPost(Constants.SLOTLIST, obj.toString());
			if(response != null){
				JSONObject objc = new JSONObject(response);
				JSONObject jobj = objc.getJSONObject("staffArray");
				JSONArray arr1 = jobj.getJSONArray("doctorArray");
				for(int i = 0;i<arr1.length();i++){
					JSONObject ob = arr1.getJSONObject(i);
					docList.add(new StuffDoctorBean(ob.getString("id"),ob.getString("doc_name"),
							ob.getString("doc_specialization"),
							ob.getString("doc_degree")));
				}		
				
				updateUi();				
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	private void updateUi() {
		base.doRemoveLoading();
		base.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				ll_doctors_container.removeAllViews();
				for(int i = 0;i< docList.size();i++){
					vi = LayoutInflater.from(base).inflate(R.layout.doctor_list_row_for_doc_availabality, null);
					vi.setId(i);
					 ll_main = (LinearLayout)vi.findViewById(R.id.ll_main);
					ll_main.setId(i);
					TextView tv_specilization = (TextView)vi.findViewById(R.id.tv_specilization);
					TextView tv_name = (TextView)vi.findViewById(R.id.tv_name);
					ImageView iv_friend_image = (ImageView)vi.findViewById(R.id.iv_friend_image);
										
					tv_specilization.setText(docList.get(i).getSpecialization());
					tv_name.setText(docList.get(i).getName());
					imageloader.DisplayImage("", iv_friend_image);
					iv_friend_image.setImageResource(R.drawable.frnd_no_img);
									
					ll_doctors_container.addView(vi);
					ll_main.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							
							int index= v.getId();
							ll_doctors_container.setVisibility(View.GONE);
							textView1_.setVisibility(View.GONE);
							ll_availanle_chart.setVisibility(View.VISIBLE);
							ll_availanle_chart.removeAllViews();
							btn_staff_profile.setText(docList.get(index).getName());
							ll_availanle_chart.addView(new DoctorAvalibility(base, docList.get(index).getId()).view);
						}
					});
				}
			
				
			}
		});
	}
	
	private LinearLayout ll_main;

}
