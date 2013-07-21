package com.heme.commonlogic.servermanager;

import java.util.HashMap;
import java.util.Map;

import com.heme.foundation.net.NetworkRequest;

public class BaseRequest {
	protected static final String HTTP_GET = "GET";
	protected static final String HTTP_POST = "POST";
	public final static int INVALID_REQUEST_ID = -1;
	private String mUrl;
	private Map<String, String> mParamMap;
	private String mHttpType;
	private int mRequestID;
	private IServerManagerListener mRequestListener;
	private NetworkRequest mRequest;
	private String mRequestKey;
	
	public BaseRequest()
	{
		super();
		mParamMap = new HashMap<String, String>();
		mHttpType = HTTP_GET;
		mRequestID = INVALID_REQUEST_ID;
	}
	
	public int getmRequestID() {
		return mRequestID;
	}
	
	public void setmRequestID(int mRequestID) {
		this.mRequestID = mRequestID;
	}
	
	public String getmUrl() {
		return mUrl;
	}

	public void setmUrl(String mUrl) {
		this.mUrl = mUrl;
	}
	
	public Map<String,String> getParams()
	{
		return mParamMap;
	}
	
	public void addStringParam(String key,String value)
	{
		if (key == null || value == null)
        {
	        return;
        }
		mParamMap.put(key, value);
	}
	
	public void addIntParam(String key, int value)
	{
		if (key == null)
        {
	        return;
        }
		mParamMap.put(key, ((Integer)value).toString());
	}
	
	public void addLongParam(String key,long value)
	{
		if (key == null)
        {
	        return;
        }
		mParamMap.put(key, ((Long)value).toString());
	}
	
	public void setHttpType(String httptype)
	{
		mHttpType = httptype;
	}
	
	public String getHttpType()
	{
		return mHttpType;
	}
	
	public IServerManagerListener getmRequestListener() {
		return mRequestListener;
	}

	public void setmRequestListener(IServerManagerListener mRequestListener) {
		this.mRequestListener = mRequestListener;
	}
	
	public NetworkRequest getmRequest() {
		return mRequest;
	}

	public void setmRequest(NetworkRequest mRequest) {
		this.mRequest = mRequest;
	}
	
	public String getmRequestKey() {
		return mRequestKey;
	}

	public void setmRequestKey(String mRequestKey) {
		this.mRequestKey = mRequestKey;
	}

}
