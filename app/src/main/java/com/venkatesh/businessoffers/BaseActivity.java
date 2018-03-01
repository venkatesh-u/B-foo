package com.venkatesh.businessoffers;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.venkatesh.businessoffers.Dialogs.CustomDialog;

public class BaseActivity extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener{

	protected MyApplication mMyApp;
	public CustomDialog dialog;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	//	overridePendingTransition(R.anim.fade_bottom_to_top,R.anim.fade_top_to_bottom);
		mMyApp = (MyApplication)this.getApplicationContext();
		dialog = new CustomDialog(this);
	}
	@Override
	protected void onResume() {
		super.onResume();
		mMyApp.setCurrentActivity(this);
	}
	protected void onPause() {
		clearReferences();
		super.onPause();
        if(dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
	}
	protected void onDestroy() {
		clearReferences();
		super.onDestroy();
	}

	private void clearReferences(){
		Activity currActivity = mMyApp.getCurrentActivity();
		if (currActivity != null && currActivity.equals(this))
			mMyApp.setCurrentActivity(null);
	}

	@Override
	public void onNetworkConnectionChanged(boolean isConnected) {
		showSnack(isConnected);
	}
	// Method to manually check connection status
	public boolean checkConnection() {
		boolean isConnected = ConnectivityReceiver.isConnected();
		showSnack(isConnected);
		return isConnected;
	}

	// Showing the status in Snackbar
	public void showSnack(boolean isConnected) {
		String message = null;
		int color = 0;
		if(!isConnected) {
			message = "Sorry! Not connected to Internet";
			color = Color.RED;
			Snackbar snackbar = Snackbar
					.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);

			View sbView = snackbar.getView();
			TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
			textView.setTextColor(color);
			snackbar.show();
		}

	}
}
