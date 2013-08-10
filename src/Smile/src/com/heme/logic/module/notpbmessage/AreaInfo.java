package com.heme.logic.module.notpbmessage;

public class AreaInfo {
	private String mProvinceName;
	private String mProvinceCode;
	private String mCityName;
	private String mCityCode;
	private String mCountryName;
	private String mCountryCode;
	
	
	public String getmProvinceCode() {
		return mProvinceCode;
	}
	
	public void setmProvinceCode(String mProvinceCode) {
		this.mProvinceCode = mProvinceCode;
	}
	
	public String getmCityCode() {
		return mCityCode;
	}
	
	public void setmCityCode(String mCityCode) {
		this.mCityCode = mCityCode;
	}
	
	public String getmCountryCode() {
		return mCountryCode;
	}
	
	public void setmCountryCode(String mCountryCode) {
		this.mCountryCode = mCountryCode;
	}

	public String getmAreaCode() {
		return mProvinceCode;
	}
	public void setmAreaCode(String mAreaCode) {
		this.mProvinceCode = mAreaCode;
	}
	
	public String getmProvinceName() {
		return mProvinceName;
	}

	public void setmProvinceName(String mProvinceName) {
		this.mProvinceName = mProvinceName;
	}

	public String getmCityName() {
		return mCityName;
	}

	public void setmCityName(String mCityName) {
		this.mCityName = mCityName;
	}

	public String getmCountryName() {
		return mCountryName;
	}

	public void setmCountryName(String mCountryName) {
		this.mCountryName = mCountryName;
	}

	
	public AreaInfo(String provinceName,String provinceCode,String cityName,String cityCode,String countryName,String countryCode)
	{
		mProvinceCode = provinceCode;
		mProvinceName = provinceName;
		mCityCode = cityCode;
		mCityName = cityName;
		mCountryCode = countryCode;
		mCountryName = countryName;
	}
}
