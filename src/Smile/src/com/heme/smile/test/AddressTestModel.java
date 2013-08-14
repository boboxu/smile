package com.heme.smile.test;

import java.io.Serializable;

import android.content.ContentValues;

public class AddressTestModel implements Serializable {
	public int id;
	public String name;
	
	public ContentValues getContentValues(){
		ContentValues cv = new ContentValues();
		cv.put("name", name);
		return cv;
	}
}
