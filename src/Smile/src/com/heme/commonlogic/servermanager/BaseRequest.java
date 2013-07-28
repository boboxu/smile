package com.heme.commonlogic.servermanager;

import com.heme.foundation.net.NetworkRequest;
import com.heme.utils.ByteUtil;

public class BaseRequest {
	protected static final String HTTP_GET = "GET";
	protected static final String HTTP_POST = "POST";
	public final static int INVALID_REQUEST_ID = -1;
	private String mUrl;
	private String mHttpType;
	private int mRequestID;
	private IServerManagerListener mRequestListener;
	private NetworkRequest mRequest;
	private String mRequestKey;
	protected byte[] mDataBuffer = null;
	
	public BaseRequest() {
		super();
//		mParamMap = new HashMap<String, String>();
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

//	public Map<String, String> getParams() {
//		return mParamMap;
//	}

//	public void addStringParam(String key, String value) {
//		if (key == null || value == null) {
//			return;
//		}
//		mParamMap.put(key, value);
//	}

//	public void addIntParam(String key, int value) {
//		if (key == null) {
//			return;
//		}
//		mParamMap.put(key, ((Integer) value).toString());
//	}

//	public void addLongParam(String key, long value) {
//		if (key == null) {
//			return;
//		}
//		mParamMap.put(key, ((Long) value).toString());
//	}

	public void setHttpType(String httptype) {
		mHttpType = httptype;
	}

	public String getHttpType() {
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

	protected void setRequestData(byte[] requestdata)
	{
		//数据的长度，转化为byte数组
		byte[] lengthdata = ByteUtil.intToByteArray(requestdata.length);
		//新建一个网络请求包，包含数据的长度，和数据两个部分
		int requestdatasize = lengthdata.length+requestdata.length;
		mDataBuffer = null;
		mDataBuffer = new byte[requestdatasize];
		//数据长度进入请求包
		for (int i = 0; i < lengthdata.length; i++) 
		{
			mDataBuffer[i] = lengthdata[i];
		}
		//数据内容进入请求包
		for (int j = 0; j < requestdata.length; j++) {
			mDataBuffer[j+lengthdata.length] = requestdata[j];  
		}
	}
}
