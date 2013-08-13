package com.heme.smile;

import java.util.Timer;
import java.util.TimerTask;

import com.heme.smile.R;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;

public class StartActivity extends Activity {
	
	private Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			gotoNextActivity();
		};
	};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);
        mHandler.sendEmptyMessageDelayed(0, 1500);
    }
    
    private void gotoNextActivity(){
    	SharedPreferences sPreferences = this.getSharedPreferences(getResources().getString(R.string.shardpreference_newuserguide), MODE_PRIVATE);
    	Intent intent = new Intent();
    	if (!sPreferences.getBoolean("guided", false)) {
			intent.setClass(StartActivity.this, NewUserGuideActivity.class);
		}else {
			intent.setClass(StartActivity.this, LoginActivity.class);
		}
    	startActivity(intent);
    	this.finish();
    }
    
}
