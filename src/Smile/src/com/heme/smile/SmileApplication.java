package com.heme.smile;

import com.heme.commonlogic.dao.DbManager;
import com.heme.foundation.net.NetworkEngine;
import com.heme.foundation.net.NetworkService;
import com.heme.smile.util.CrashHandler;
import com.heme.utils.FileUtil;

import android.app.Application;

public class SmileApplication extends Application {
	public static String APPPATH;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		CrashHandler crashHandler = CrashHandler.getInstance();
		crashHandler.init(getApplicationContext());
		DbManager.initDbManager(this);
        NetworkEngine.getEngine();
        NetworkService.actionStart(this);
        
        APPPATH = getApplicationContext().getFilesDir().getAbsolutePath();
        FileUtil.ensureDir(FileUtil.getFullAppDataPath());
	}
	
	
}
