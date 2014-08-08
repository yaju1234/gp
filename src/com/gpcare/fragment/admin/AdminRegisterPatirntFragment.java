package com.gpcare.fragment.admin;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.gpcare.bean.UserBean;
import com.gpcare.constants.Constants;
import com.gpcare.model.UserListView;
import com.gpcare.model.UserListener;
import com.gpcare.model.admin.SignUpView;
import com.gpcare.model.admin.UserProfile;
import com.gpcare.network.HttpClient;
import com.gpcare.screen.BaseScreen;
import com.gpcare.screen.R;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.LinearLayout;

@SuppressLint("ValidFragment")
public class AdminRegisterPatirntFragment extends Fragment implements OnClickListener, UserListener{
	
	private BaseScreen base;
	private LinearLayout ll_user_list = null,ll_main = null;
	private Button btn_add_new_user = null;
	private ArrayList<UserBean> userArr = new ArrayList<UserBean>();
	
	
	public AdminRegisterPatirntFragment(BaseScreen b){
		base = b;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.admin_user_profile_list, container, false);
		ll_user_list = (LinearLayout)view.findViewById(R.id.ll_user_list);
		ll_main = (LinearLayout)view.findViewById(R.id.ll_main);
		btn_add_new_user = (Button)view.findViewById(R.id.btn_add_new_user);
		btn_add_new_user.setOnClickListener(this);
		callWebService();
		
		return view;
	}
	
	
	private void callWebService() {
		ll_user_list.removeAllViews();
		base.doShowLoading();
		Thread t = new Thread(){
			public void run(){
				JSONObject request = new JSONObject();
				try {
					request.put("admin", "admin");			
					String response = HttpClient.SendHttpPost(Constants.USER_LIST, request.toString());
					if(response != null){
						JSONObject jsonres = new JSONObject(response);
						JSONArray jArr = jsonres.getJSONArray("userArray");
						for(int i = 0; i<jArr.length(); i++){
							JSONObject c = jArr.getJSONObject(i);
							 String regNo = c.getString("reg_no");
							 String name = c.getString("name"); 
							 String  userName= c.getString("username");
							 String dob= c.getString("dob");
							 String address= c.getString("address");
							 String contact= c.getString("contact");
							 String emrg_contact= c.getString("emrg_contact");
							 String image= c.getString("image");							 
							 userArr.add(new UserBean(regNo, name, userName, dob, address, contact, emrg_contact, image));
						}
						base.doRemoveLoading();
						base.runOnUiThread(new Runnable() {						
							public void run() {								
								for(int i = 0; i<userArr.size(); i++)
								ll_user_list.addView(new UserListView(base, userArr.get(i).getName(),userArr.get(i).getRegNo(),AdminRegisterPatirntFragment.this).mView);
							}
						});
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		};
		t.start();
		
		
	
		
	}

	@Override
	public void onUserListClick(String regid) {
		Log.e("regid", regid);
		int i = 0;
		ll_user_list.removeAllViews();
		for(int j = 0; j<userArr.size(); j++){
			if(userArr.get(j).getRegNo().equalsIgnoreCase(regid)){
				i=j;
				
				break;
			}			
			
		}
		ll_user_list.addView(new UserProfile(base, regid,userArr.get(i).getImage(), userArr.get(i).getName(), userArr.get(i).getUserName(), userArr.get(i).getAddress(), userArr.get(i).getDob(), userArr.get(i).getContact(), userArr.get(i).getEmrg_contact()).mView);
	}

	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		case R.id.btn_add_new_user:
			ll_main.removeAllViews();
			ll_main.addView(new SignUpView(base).mView);
			break;

		default:
			break;
		}
	}

}
