package com.heme.smile;

import android.app.Application;

import com.heme.commonlogic.dao.DbManager;
import com.heme.foundation.net.NetworkEngine;
import com.heme.foundation.net.NetworkService;
import com.heme.logic.managers.pushmanager.PushManager;
import com.heme.smile.util.CrashHandler;
import com.heme.utils.FileUtil;

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
        PushManager.shareManager();
        
        APPPATH = getApplicationContext().getFilesDir().getAbsolutePath();
        FileUtil.ensureDir(FileUtil.getFullAppDataPath());
	}
	
	
}
