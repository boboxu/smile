package com.heme.smile.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 *
 */
public class DatabaseProvider extends SQLiteOpenHelper {
	private static final String TAG = "DatabaseProvider";
	private static final int DB_VERSION = 1;
	private static final String DBNAME = "smile.db";
	
	
	private static final String ADDRESS_TABLE_NAME = "address";
	private static final String ADDRESS_CREATE_SQL = "create table if not exists "+ADDRESS_TABLE_NAME+" (_id integer primary key autoincrement,name text,serverId integer,groupId integer)";
	
	
	private SQLiteDatabase db;
	public DatabaseProvider(Context ctx){
		super(ctx, DBNAME, null, DB_VERSION);
	}
	public DatabaseProvider(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}
	public Cursor getContacterList(String whereSql,String[] selectionArgs){
		SQLiteDatabase sqLiteDatabase = getWritableDatabase();
		Cursor cursor = sqLiteDatabase.query(ADDRESS_TABLE_NAME, new String[]{"_id","name","serverId","groupId"}, whereSql, selectionArgs, null, null, "_id desc");
		return cursor;
	}
	public synchronized long insertAddressinfo(ContentValues cv){
		long result = 0;
		SQLiteDatabase sqLiteDatabase = getWritableDatabase();
		Cursor cursor = sqLiteDatabase.query(ADDRESS_TABLE_NAME, new String[]{"_id","name"}, " name = ? and serverId = ? ", new String[]{cv.getAsString("name"),cv.getAsInteger("serverId")+""}, null, null, "_id desc");
		if (cursor!=null&&cursor.moveToFirst()) {
			sqLiteDatabase.delete(ADDRESS_TABLE_NAME, " name = ? and serverId = ? ",new String[]{cv.getAsString("name"),cv.getAsInteger("serverId")+""});
		}
		if (cursor!=null) {
			cursor.close();
		}
		result = sqLiteDatabase.insert(ADDRESS_TABLE_NAME, null, cv);
		sqLiteDatabase.close();
		return result;
	}
//	public synchronized AddressTestModel queryOfflineUserInfo(String username,String pwd){
//		AddressTestModel userInfo = null;
//		SQLiteDatabase sqLiteDatabase = getReadableDatabase();
//		Cursor cursor = sqLiteDatabase.query(USERINFO_TABLE_NAME, new String[]{"_id","username","realname","pwd"}, " username = ? and pwd = ? ", new String[]{username,pwd}, null, null, "_id desc");
//		if (cursor!=null&&cursor.moveToFirst()) {
//			userInfo = new UserInfo();
//			int idindex = cursor.getColumnIndex("_id");
//			int usernameindex = cursor.getColumnIndex("username");
//			int pwdindex = cursor.getColumnIndex("pwd");
//			int realnameindex = cursor.getColumnIndex("realname");
//			userInfo.mId = cursor.getInt(idindex);
//			userInfo.mUserName = cursor.getString(usernameindex);
//			userInfo.mPassword = cursor.getString(pwdindex);
//			userInfo.mRealName = cursor.getString(realnameindex);
//		}
//		if (cursor!=null) {
//			cursor.close();
//		}
//		sqLiteDatabase.close();
//		return userInfo;
//	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		this.db = db;
		this.db.execSQL(ADDRESS_CREATE_SQL);
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int arg1, int arg2) {
		database.execSQL("drop table if exitst "+ADDRESS_CREATE_SQL);
		onCreate(database);
	}
}
