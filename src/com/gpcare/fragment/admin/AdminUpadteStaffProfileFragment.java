package com.gpcare.fragment.admin;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.gpcare.bean.DoctorBean;
import com.gpcare.bean.NurseBean;
import com.gpcare.bean.StuffBean;
import com.gpcare.constants.Constants;
import com.gpcare.model.admin.StuffSignUpView;
import com.gpcare.network.HttpClient;
import com.gpcare.screen.BaseScreen;
import com.gpcare.screen.R;
import com.gpcare.settings.ImageLoader;
import com.gpcare.settings.Utility;

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
import android.widget.RelativeLayout;
import android.widget.TextView;

@SuppressLint("ValidFragment")
public class AdminUpadteStaffProfileFragment extends Fragment implements OnClickListener{
	
	private BaseScreen base;
	private Button btn_staff_profile;
	public ArrayList<DoctorBean> docList = new ArrayList<DoctorBean>();
	public ArrayList<NurseBean> nurseList = new ArrayList<NurseBean>();
	public ArrayList<StuffBean> stuffList = new ArrayList<StuffBean>();
	
	private LinearLayout ll_doctors_container;
	private LinearLayout ll_nurse_container;
	private LinearLayout ll_stuff_container;
	private ImageLoader imageloader;
	private LinearLayout ll_body;
	private View vi;
	
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
				getAllstuffList();
				
			}
		};
		t.start();
	}
	private void getAllstuffList() {
		try {
			base.doShowLoading();
			//JSONObject obj = new JSONObject(Utility.readXMLinString("stuff.txt", base));
			String response = HttpClient.SendHttpPost(Constants.FETCH_ALL_DOCTOR, "");
			if(response != null){
				JSONObject jsonres = new JSONObject(response);
				if(jsonres.getBoolean("status")){
					JSONArray jArr = jsonres.getJSONArray("doctorArray");
					for(int i=0; i<jArr.length(); i++){
						JSONObject c = jArr.getJSONObject(i);
						String id = c.getString("id");
						String fname = c.getString("fname");
						String lname = c.getString("lname");
						String username = c.getString("username");
						String specialization = c.getString("specialization");
						String degree = c.getString("degree");
						docList.add(new DoctorBean(id, fname, lname, username, specialization, degree));
						
					}
				}
				
				
				/*JSONArray arr2 = jobj.getJSONArray("nurseArray");
				for(int i = 0;i<arr2.length();i++){
					JSONObject ob = arr2.getJSONObject(i);
					nurseList.add(new NurseBean(ob.getString("nurse_name"),
							ob.getString("nurse_specialization"),
							ob.getString("nurse_degree")));
				}
				
				JSONArray arr3 = jobj.getJSONArray("supportstuffArray");
				for(int i = 0;i<arr3.length();i++){
					JSONObject ob = arr3.getJSONObject(i);
					stuffList.add(new StuffBean(ob.getString("stuff_name"),
							ob.getString("stuff_specialization"),
							ob.getString("stuff_degree")));
				}*/
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
					vi = LayoutInflater.from(base).inflate(R.layout.doctor_list_row, null);
					TextView tv_specilization = (TextView)vi.findViewById(R.id.tv_specilization);
					TextView tv_name = (TextView)vi.findViewById(R.id.tv_name);
					ImageView iv_friend_image = (ImageView)vi.findViewById(R.id.iv_friend_image);
										
					tv_specilization.setText(docList.get(i).getSpecialization());
					tv_name.setText(docList.get(i).getFirstName()+" "+docList.get(i).getLastName());
					//imageloader.DisplayImage("", iv_friend_image);
					iv_friend_image.setImageResource(R.drawable.frnd_no_img);
									
					ll_doctors_container.addView(vi);
				}
				
				/*ll_nurse_container.removeAllViews();
				for(int i = 0;i< nurseList.size();i++){
					vi = LayoutInflater.from(base).inflate(R.layout.doctor_list_row, null);
					TextView tv_specilization = (TextView)vi.findViewById(R.id.tv_specilization);
					TextView tv_name = (TextView)vi.findViewById(R.id.tv_name);
					ImageView iv_friend_image = (ImageView)vi.findViewById(R.id.iv_friend_image);
										
					tv_specilization.setText(nurseList.get(i).getnurse_specilization());
					tv_name.setText(nurseList.get(i).getnurse_name());
					//imageloader.DisplayImage("", iv_friend_image);
					iv_friend_image.setImageResource(R.drawable.frnd_no_img);
									
					ll_nurse_container.addView(vi);
				}*/
				
				/*ll_stuff_container.removeAllViews();
				for(int i = 0;i< stuffList.size();i++){
					vi = LayoutInflater.from(base).inflate(R.layout.doctor_list_row, null);
					TextView tv_specilization = (TextView)vi.findViewById(R.id.tv_specilization);
					TextView tv_name = (TextView)vi.findViewById(R.id.tv_name);
					ImageView iv_friend_image = (ImageView)vi.findViewById(R.id.iv_friend_image);
										
					tv_specilization.setText(stuffList.get(i).getstuff_specilization());
					tv_name.setText(stuffList.get(i).getstuff_name());
					//imageloader.DisplayImage("", iv_friend_image);
					iv_friend_image.setImageResource(R.drawable.frnd_no_img);
									
					ll_stuff_container.addView(vi);
				}*/
			}
		});
	}

}
