package com.heme.logic;

import java.util.HashMap;
import java.util.Map;

import android.util.Log;

import com.heme.logic.managers.accountmanager.AccountManager;
import com.heme.logic.managers.base.BaseBusinessLogicManager;
import com.heme.logic.managers.friendmanager.FriendManager;
import com.heme.logic.managers.groupmanager.GroupManager;
import com.heme.logic.managers.loginmanager.LoginManager;
import com.heme.logic.managers.registmanager.RegistManager;

public class LogicManager {
	private final static String TAG = "LogicManager";
	private final static Map<String, BaseBusinessLogicManager> mManagerMap = new HashMap<String, BaseBusinessLogicManager>();
	
	@SuppressWarnings("unused")
	private static BaseBusinessLogicManager findManager(Class<?> logicManagerClass)
	{
		BaseBusinessLogicManager logicManager = null;
		String className = logicManagerClass.getName();
		if (mManagerMap.containsKey(className))
		{
			logicManager = (BaseBusinessLogicManager) mManagerMap.get(className);
		}
		if (logicManager == null)
		{
			Log.d(TAG, String.format("findByLogicManagerClass"+logicManagerClass.getName()));
			try
			{
				logicManager = (BaseBusinessLogicManager) logicManagerClass.newInstance();
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
	
	public static FriendManager friendManager()
	{
		return (FriendManager) findManager(FriendManager.class);
	}
	
	public static GroupManager groupManager()
	{
		return (GroupManager) findManager(GroupManager.class);
	}
	
	public static RegistManager registManager()
	{
		return (RegistManager) findManager(RegistManager.class);
	}
}
