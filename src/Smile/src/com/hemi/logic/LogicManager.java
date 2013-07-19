package com.hemi.logic;

import java.util.HashMap;
import java.util.Map;

import android.util.Log;

import com.hemi.logic.managers.base.BaseLogicManager;
import com.hemi.logic.managers.loginmanager.LoginManager;

public class LogicManager {
	private final static String TAG = "LogicManager";
	private final static Map<String, BaseLogicManager> mManagerMap = new HashMap<String, BaseLogicManager>();
	
	@SuppressWarnings("unused")
	private static BaseLogicManager findManager(Class<?> logicManagerClass)
	{
		BaseLogicManager logicManager = null;
		String className = logicManagerClass.getName();
		if (mManagerMap.containsKey(className))
		{
			logicManager = (BaseLogicManager) mManagerMap.get(className);
		}
		if (logicManager == null)
		{
			Log.d(TAG, String.format("findByLogicManagerClass"+logicManagerClass.getName()));
			try
			{
				logicManager = (BaseLogicManager) logicManagerClass.newInstance();
			}
			catch (IllegalAccessException e)
			{
				Log.e(TAG, e.getMessage());
			}
			catch (InstantiationException e)
			{
				Log.e(TAG, e.getMessage());
			}
			if (logicManager != null)
			{
				mManagerMap.put(className, logicManager);
				Log.d(TAG, String.format("findByLogicManagerClass result: %s", logicManager.getClass().getName()));
			}
			else
			{
				Log.e(TAG, String.format("fail to find logicmanager %s", className));
			}
		}
		
		return logicManager;
	}
	
	public static LoginManager loginManager()
	{
		return (LoginManager) findManager(LoginManager.class);
	}
}
