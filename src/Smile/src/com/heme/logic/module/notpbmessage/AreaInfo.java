package com.heme.logic.module.notpbmessage;

public class AreaInfo {
	private String mAreaName;
	private String mAreaCode;
	
	public String getmAreaName() {
		return mAreaName;
	}
	public void setmAreaName(String mAreaName) {
		this.mAreaName = mAreaName;
	}
	public String getmAreaCode() {
		return mAreaCode;
	}
	public void setmAreaCode(String mAreaCode) {
		this.mAreaCode = mAreaCode;
	}
	
	public AreaInfo(String name,String code)
	{
		mAreaCode = code;
		mAreaName = name;
	}
}
