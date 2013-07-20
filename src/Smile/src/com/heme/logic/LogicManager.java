package com.heme.logic;

import java.util.HashMap;
import java.util.Map;

import android.util.Log;

import com.heme.logic.managers.accountmanager.AccountManager;
import com.heme.logic.managers.base.BusinessLogicManager;
import com.heme.logic.managers.loginmanager.LoginManager;

public class LogicManager {
	private final static String TAG = "LogicManager";
	private final static Map<String, BusinessLogicManager> mManagerMap = new HashMap<String, BusinessLogicManager>();
	
	@SuppressWarnings("unused")
	private static BusinessLogicManager findManager(Class<?> logicManagerClass)
	{
		BusinessLogicManager logicManager = null;
		String className = logicManagerClass.getName();
		if (mManagerMap.containsKey(className))
		{
			logicManager = (BusinessLogicManager) mManagerMap.get(className);
		}
		if (logicManager == null)
		{
			Log.d(TAG, String.format("findByLogicManagerClass"+logicManagerClass.getName()));
			try
			{
				logicManager = (BusinessLogicManager) logicManagerClass.newInstance();
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
	
	public static AccountManager accountManager()
	{
		return (AccountManager) findManager(AccountManager.class);
	}
	
}
