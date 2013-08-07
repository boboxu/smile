package com.heme.smile;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.heme.foundation.net.NetworkEngine;
import com.heme.foundation.net.NetworkService;

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
        
        NetworkEngine.getEngine();
        
        NetworkService.actionStart(this);
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
