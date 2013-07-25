package com.heme.foundation.sqlite;

import java.util.HashMap;
import java.util.Map;

import com.heme.utils.StringUtil;

import android.content.Context;
import android.content.ContextWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteDatabaseOpenHelper extends SQLiteOpenHelper
{
	protected static final String TAG = "SQLiteDatabaseOpenHelper";

	// 数据库版本号,实体有变化需要修改版本号
	protected static int DATABASE_VERSION = 1;
	
	protected static Map<String, SQLiteDatabaseOpenHelper> mDatabaseOpenHelperMap = new HashMap<String, SQLiteDatabaseOpenHelper>();
	
	public static synchronized SQLiteDatabaseOpenHelper getInstance(Context context, String dbName)
	{
		if (mDatabaseOpenHelperMap.get(dbName) == null)
		{
			if (context != null && !StringUtil.isEmptyOrNull(dbName))
			{
				ContextWrapper contextWrapper = new ContextWrapper(context);
				SQLiteDatabaseOpenHelper openHelper = new SQLiteDatabaseOpenHelper(
						contextWrapper, dbName, null, DATABASE_VERSION);
				mDatabaseOpenHelperMap.put(dbName, openHelper);
			}
			else
			{
				Log.e(TAG, String.format(
				        "context(%s) or databaseName(%s) is null",
				        context, dbName));
			}
		}
		return mDatabaseOpenHelperMap.get(dbName);
	}
	
	public SQLiteDatabaseOpenHelper(Context context, String name,
			CursorFactory factory, int version)
	{
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2)
	{
		// TODO Auto-generated method stub
		
	}

}
