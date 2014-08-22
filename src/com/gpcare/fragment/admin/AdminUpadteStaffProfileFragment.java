package com.gpcare.fragment.admin;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gpcare.bean.NurseBean;
import com.gpcare.bean.StuffBean;
import com.gpcare.bean.StuffDoctorBean;
import com.gpcare.constants.Constants;
import com.gpcare.model.admin.StuffSignUpView;
import com.gpcare.network.HttpClient;
import com.gpcare.screen.BaseScreen;
import com.gpcare.screen.R;
import com.gpcare.settings.ImageLoader;

@SuppressLint("ValidFragment")
public class AdminUpadteStaffProfileFragment extends Fragment implements OnClickListener{
	
	private BaseScreen base;
	private Button btn_staff_profile;
	public ArrayList<StuffDoctorBean> docList = new ArrayList<StuffDoctorBean>();
	public ArrayList<NurseBean> nurseList = new ArrayList<NurseBean>();
	public ArrayList<StuffBean> stuffList = new ArrayList<StuffBean>();
	
	private LinearLayout ll_doctors_container;
	private LinearLayout ll_nurse_container;
	private LinearLayout ll_stuff_container;
	private ImageLoader imageloader;
	private LinearLayout ll_body;
	private View vi;
	
	private String type  = null;
	
	public AdminUpadteStaffProfileFragment(BaseScreen b){
		base = b;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.staff_profile, container, false);
		btn_staff_profile = (Button)view.findViewById(R.id.btn_staff_profile);
		btn_staff_profile.setOnClickListener(this);
		ll_doctors_container = (LinearLayout)view.findViewById(R.id.ll_doctors_container);
		ll_nurse_container = (LinearLayout)view.findViewById(R.id.ll_nurse_container);
		ll_stuff_container = (LinearLayout)view.findViewById(R.id.ll_stuff_container);
		ll_body = (LinearLayout)view.findViewById(R.id.ll_body);
		imageloader = new ImageLoader(base);
		
		getstaffList();
		return view;
	}

	
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_staff_profile:
			//btn_staff_profile.setVisibility(View.GONE);
			ll_body.removeAllViews();
			ll_body.addView(new StuffSignUpView(base).mView);
			break;

		default:
			break;
		}
		
	}
	
	private void getstaffList() {
		Thread t = new Thread(){
			public void run(){
				docList.clear();
				nurseList.clear();
				stuffList.clear();
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
				
				JSONArray arr2 = jobj.getJSONArray("nurseArray");
				for(int i = 0;i<arr2.length();i++){
					JSONObject ob = arr2.getJSONObject(i);
					nurseList.add(new NurseBean(ob.getString("nurse_id"),
							ob.getString("nurse_name"),
							ob.getString("nurse_specialization"),
							ob.getString("nurse_degree")));
				}
				
				JSONArray arr3 = jobj.getJSONArray("supportstuffArray");
				for(int i = 0;i<arr3.length();i++){
					JSONObject ob = arr3.getJSONObject(i);
					stuffList.add(new StuffBean(ob.getString("stuff_id"),
							ob.getString("stuff_name"),
							ob.getString("stuff_specialization"),
							ob.getString("stuff_degree")));
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
					final int j = i;
					vi = LayoutInflater.from(base).inflate(R.layout.doctor_list_row_update, null);
					TextView tv_specilization = (TextView)vi.findViewById(R.id.tv_specilization);
					TextView tv_name = (TextView)vi.findViewById(R.id.tv_name);
					ImageView iv_friend_image = (ImageView)vi.findViewById(R.id.iv_friend_image);
					Button btn_delete = (Button)vi.findViewById(R.id.btn_delete);
					
					btn_delete.setOnClickListener(new OnClickListener() {						
						@Override
						public void onClick(View v) {
							type = "doc";
							deleteProfile(type,docList.get(j).getId());
						}
					});
										
					tv_specilization.setText(docList.get(i).getSpecialization());
					tv_name.setText(docList.get(i).getName());
					imageloader.DisplayImage("", iv_friend_image);
					iv_friend_image.setImageResource(R.drawable.frnd_no_img);
									
					ll_doctors_container.addView(vi);
				}
				
				ll_nurse_container.removeAllViews();
				for(int i = 0;i< nurseList.size();i++){
					final int j = i;
					vi = LayoutInflater.from(base).inflate(R.layout.doctor_list_row_update, null);
					TextView tv_specilization = (TextView)vi.findViewById(R.id.tv_specilization);
					TextView tv_name = (TextView)vi.findViewById(R.id.tv_name);
					ImageView iv_friend_image = (ImageView)vi.findViewById(R.id.iv_friend_image);
					Button btn_delete = (Button)vi.findViewById(R.id.btn_delete);
					
					btn_delete.setOnClickListener(new OnClickListener() {						
						@Override
						public void onClick(View v) {
							type = "stuff";
							deleteProfile(type,nurseList.get(j).getNurse_id());
						}
					});
										
					tv_specilization.setText(nurseList.get(i).getNurse_specilization());
					tv_name.setText(nurseList.get(i).getNurse_name());
					//imageloader.DisplayImage("", iv_friend_image);
					iv_friend_image.setImageResource(R.drawable.frnd_no_img);
									
					ll_nurse_container.addView(vi);
				}
				
				ll_stuff_container.removeAllViews();
				for(int i = 0;i< stuffList.size();i++){
					final int j = i;
					vi = LayoutInflater.from(base).inflate(R.layout.doctor_list_row_update, null);
					TextView tv_specilization = (TextView)vi.findViewById(R.id.tv_specilization);
					TextView tv_name = (TextView)vi.findViewById(R.id.tv_name);
					ImageView iv_friend_image = (ImageView)vi.findViewById(R.id.iv_friend_image);
					Button btn_delete = (Button)vi.findViewById(R.id.btn_delete);
										
					btn_delete.setOnClickListener(new OnClickListener() {						
						@Override
						public void onClick(View v) {
							type = "stuff";
							deleteProfile(type,stuffList.get(j).getStuff_id());
						}
					});
					
					tv_specilization.setText(stuffList.get(i).getStuff_specilization());
					tv_name.setText(stuffList.get(i).getStuff_name());
					//imageloader.DisplayImage("", iv_friend_image);
					iv_friend_image.setImageResource(R.drawable.frnd_no_img);
									
					ll_stuff_container.addView(vi);
				}
			}
		});
	}
	private void deleteProfile(final String type, final String id) {
		Thread t = new Thread(){
			public void run(){
				base.doShowLoading();
				callWebService(type,id);
				base.doRemoveLoading();
			}
		};
		t.start();
	}
	private void callWebService(String type2, String id) {
		String response = null;
		JSONObject obj = new JSONObject();
		try {
			
			if(type2.equalsIgnoreCase("doc")){
				obj.put("docid", id);
				response = HttpClient.SendHttpPost(Constants.DOCTOR_DELETE, obj.toString());
			}else if(type2.equalsIgnoreCase("stuff")){
				obj.put("staffid", id);
				response = HttpClient.SendHttpPost(Constants.STUFF_DELETE, obj.toString());
			}
			
			if(response != null){
				JSONObject OB = new JSONObject(response);
				if(OB.getBoolean("status")){
					base.runOnUiThread(new Runnable() {
						
						@Override
						public void run() {
							Toast.makeText(base, "Deleted successfully", 3000).show();
							getstaffList();
						}
					});
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
