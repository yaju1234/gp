package com.gpcare.screen;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.gpcare.model.AdminProfile.OnAdminListeber;
import com.gpcare.model.admin.EditAdminProfileView.onUpdateAdminProfileListener;
import com.gpcare.model.admin.SignInView.OnAdminSignInListener;
import com.gpcare.settings.AdminInfo;
import com.gpcare.settings.Appsettings;
import com.gpcare.settings.UserInfo;

public class BaseScreen extends FragmentActivity implements OnClickListener,OnAdminSignInListener,OnAdminListeber,onUpdateAdminProfileListener{

	public Appsettings app = null;
	public ProgressDialog dialog;
	
	
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		app = (Appsettings)getApplication();
		if(!app.init){
			app.init = true;
			app.setUserinfo(new UserInfo(this));
			app.setAdmininfo(new AdminInfo(this));
		}
	}
	
	/*protected void onStart() {
		super.onStart();
		app = (Appsettings)getApplication();
		if(!app.init){
			app.init = true;
			app.setUserinfo(new UserInfo(this));
			app.setAdmininfo(new AdminInfo(this));
		}
		init();
	}*/
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
	
	public void goNextScreen(final boolean flg){}
	public void onAdminSignIn(String id, String fname, String lname,String email, String image) {
		Toast.makeText(getApplicationContext(), "Hello", 3000).show();
	}
	public void onLogout() {}
	public void onUpdateAdminProfile() {}

}
