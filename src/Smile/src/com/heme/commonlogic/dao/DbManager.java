package com.heme.commonlogic.dao;

import com.heme.logic.module.DaoMaster;
import com.heme.logic.module.DaoMaster.DevOpenHelper;
import com.heme.logic.module.DaoSession;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DbManager
{
	protected static final String DB_NAME = "Smile.db";
	protected static DbManager gDbManager = null;
	
	protected SQLiteDatabase mDb = null;
	protected DaoMaster mDaoMaster = null;
	protected DaoSession mDaoSession = null;
	
	public static void initDbManager(Context context)
	{
		gDbManager = new DbManager(context, DB_NAME);
	}
	
	public static DbManager getDbManager()
	{
		return gDbManager;
	}
	
	protected DbManager(Context context, String db)
	{
		DevOpenHelper helper = new DevOpenHelper(context, db, null);
		mDb = helper.getWritableDatabase();
		
		if (mDb == null) return;
		
		mDaoMaster = new DaoMaster(mDb);
		mDaoSession = mDaoMaster.newSession();
	}

	public DaoMaster getmDaoMaster()
	{
		return mDaoMaster;
	}

	public DaoSession getmDaoSession()
	{
		return mDaoSession;
	}
}
