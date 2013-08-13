package com.heme.commonlogic.dao;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;

/**
 * 暂时先不用这个
 *
 */

public class DaoManager
{
	//账户数据库名
	public static final String USER_DATABASE = "user_%s.db";
	
	protected static DaoManager gShareManager = null;
	
	protected Context mContext = null;
	
	protected Map<String, DbManager> mDatabaseMap = new HashMap<String, DbManager>();
	
	protected static Object mLockObject = new Object();
	
	//需要在启动时初始化
	public static void initDaoManager(Context context)
	{
		gShareManager = new DaoManager();
		gShareManager.mContext = context;
	}
	
	public static DaoManager shareManager()
	{
		return gShareManager;
	}
	
	public DbManager getDbManager(String dbName)
	{
		if (mDatabaseMap.get(dbName) == null)
		{
			synchronized (mLockObject)
			{
				DbManager dbManager = new DbManager(mContext, dbName);
				mDatabaseMap.put(dbName, dbManager);
			}
			
		}
		return mDatabaseMap.get(dbName);
	}
	
}
