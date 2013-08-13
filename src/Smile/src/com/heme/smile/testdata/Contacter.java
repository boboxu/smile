package com.heme.smile.testdata;

import android.content.ContentValues;

//用于UI开发时的好友数据结构
public class Contacter {
	public Integer id;
	public String name;
	public Integer serverId;
	public Integer groupId;
	
	public ContentValues getContentValues(){
		ContentValues cv = new ContentValues();
		cv.put("name", name);
		cv.put("serverId", serverId);
		cv.put("groupId", groupId);
		return cv;
	}
}
