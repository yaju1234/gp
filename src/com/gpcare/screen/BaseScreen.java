package com.gpcare.screen;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.gpcare.settings.Appsettings;
import com.gpcare.settings.LoginInfo;
import com.gpcare.settings.UserInfo;

public class BaseScreen extends FragmentActivity implements OnClickListener{

	public Appsettings app = null;
	public ProgressDialog dialog;
	@Override
	protected void onStart() {
		super.onStart();
		app = (Appsettings)getApplication();
		if(!app.init){
			app.init = true;
			app.setLoginfo(new LoginInfo(this));
			app.setUserinfo(new UserInfo(this));
		}
		init();
	}
	public void init() {}
	
	@Override
	public void onClick(View v) {}
	
	public void hideKeyBoard(EditText et){
		InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(et.getWindowToken(), 0);	
	}
	public void doShowLoading(){
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				dialog = new ProgressDialog(BaseScreen.this);
				dialog.setMessage("Please wait..........");
				dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
				dialog.setIndeterminate(true);
				dialog.setCancelable(true);
				dialog.show();
			}
		});
	}
	
	public void doRemoveLoading(){
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				dialog.cancel();
			}
		});
	}
	
	/*public void doLogin(String username,String password){
		try {
			JSONObject object = new JSONObject();
			object.put("user_id", username);
			object.put("password", password);
			String result = HttpClient.SendHttpPost("http://clickfordevelopers.com/demo/seedstir/log_in.php", object.toString());
			if(result != null){
				JSONObject obj = new JSONObject(result);
				JSONObject jobj = obj.getJSONObject("status");
				int status_code = jobj.getInt("status_code");
				if(status_code == 1){
					if(obj.getString("type").equalsIgnoreCase("1")){
						JSONObject jobject = obj.getJSONObject("data");
						JSONObject ob = jobject.getJSONObject("user");
						String user_id = ob.getString("user_id");
						String profileuserID = ob.getString("profileuserID");
						String email = ob.getString("email");
						String gender = null;
						if(ob.isNull("gender")){
							gender = "";
						}else{
							gender = ob.getString("gender");
						}					
						String first_name = null;
						if(ob.isNull("first_name")){
							first_name = "";
						}else{
							first_name = ob.getString("first_name");
						}
						String last_name = null;
						if(ob.isNull("last_name")){
							last_name = "";
						}else{
							last_name = ob.getString("last_name");
						}
						String address = null;
						if(ob.isNull("address")){
							address = "";
						}else{
							address = ob.getString("address");
						}
						String profile_picture = null;
						if(ob.isNull("profile_picture")){
							profile_picture = "";
						}else{
							profile_picture = ob.getString("profile_picture");
						}
						String profile_desc = null;
						if(ob.isNull("profile_desc")){
							profile_desc = "";
						}else{
							profile_desc = ob.getString("profile_desc");
						}
						String university = null;
						if(ob.isNull("university")){
							university = "";
						}else{
							university = ob.getString("university");
						}
						
						app.getUserinfo().SetUserInfo(gender, email, user_id, profileuserID,first_name,last_name,address,profile_picture,profile_desc,university);
						app.getLoginfo().setLoginInfo(username, password);
						
						Constants.STATUS_CODE = 1;
						
						Constants.preference = getSharedPreferences(Constants.values.IS_SIGNIN.name(), Context.MODE_PRIVATE);
						Editor edit = Constants.preference.edit();
						edit.putBoolean(Constants.values.USER_SIGNIN_STATUS.name(), true);
						edit.commit();					
						
						goNextScreen(true);
					}else{
						JSONObject jobject = obj.getJSONObject("data");
						JSONObject ob = jobject.getJSONObject("user");
						String user_id = ob.getString("user_id");
						String profileuserID = ob.getString("profileuserID");
						String email = ob.getString("email");
						
						String company_name = null;
						if(ob.isNull("company_name")){
							company_name = "";
						}else{
							company_name = ob.getString("company_name");
						}
						String logo = null;
						if(ob.isNull("logo")){
							logo = "";
						}else{
							logo = ob.getString("logo");
						}
						String address = null;
						if(ob.isNull("address")){
							address = "";
						}else{
							address = ob.getString("address");
						}
						String company_desc = null;
						if(ob.isNull("company_desc")){
							company_desc = "";
						}else{
							company_desc = ob.getString("company_desc");
						}
						
						String company_type = null;
						if(ob.isNull("company_type")){
							company_type = "";
						}else{
							company_type = ob.getString("company_type");
						}
						
						app.getLoginfo().setLoginInfo(username, password);
						
						Constants.STATUS_CODE = 2;
						
						Constants.preference = getSharedPreferences(Constants.values.IS_SIGNIN.name(), Context.MODE_PRIVATE);
						Editor edit = Constants.preference.edit();
						edit.putBoolean(Constants.values.COMPANY_SIGNIN_STATUS.name(), true);
						edit.commit();					
						
						goNextScreen(true);
					}					
				}else if(status_code == 0){
					goNextScreen(false);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}*/
	public void goNextScreen(final boolean flg){}
}
