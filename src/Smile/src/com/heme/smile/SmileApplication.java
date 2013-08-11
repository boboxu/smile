package com.heme.smile;

import com.heme.commonlogic.dao.DbManager;
import com.heme.foundation.net.NetworkEngine;
import com.heme.foundation.net.NetworkService;

import android.app.Application;

public class SmileApplication extends Application {
	public static String APPPATH;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		DbManager.initDbManager(this);
        NetworkEngine.getEngine();
        NetworkService.actionStart(this);
        
        APPPATH = getApplicationContext().getFilesDir().getAbsolutePath();
	}
	
	
}
