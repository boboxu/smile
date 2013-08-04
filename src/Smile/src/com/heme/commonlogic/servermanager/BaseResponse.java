package com.heme.commonlogic.servermanager;

import android.util.Log;

import com.google.protobuf.InvalidProtocolBufferException;
import com.heme.foundation.error.BaseError;
import com.heme.utils.ByteUtil;

public class BaseResponse {
	private final static String TAG = "BaseResponse";
	protected BaseRequest mRequest;
	protected BaseError mError;
	private byte[] mDataBuffer;
	protected byte[] mRespData;
	protected int mRet;
	
	public final static int RET_SUCCESS = 0;
	public final static int RET_ERROR = -1;
	public int getmRet() {
		return mRet;
	}

	public void setmRet(int mRet) {
		this.mRet = mRet;
	}

	public byte[] getmDataBuffer() {
		return mDataBuffer;
	}

	public void setmDataBuffer(byte[] mDataBuffer) {
		this.mDataBuffer = mDataBuffer;
	}

	public BaseError getmError() {
		return mError;
	}

	public void setmError(BaseError mError) {
		this.mError = mError;
	}

	public BaseRequest getmRequest() {
		return mRequest;
	}

	public void setmRequest(BaseRequest mRequest) {
		this.mRequest = mRequest;
	}
	
	protected void setResponseData(byte[] responsedata)
	{
		//数据的长度，转化为byte数组
		byte[] lengthdata = ByteUtil.intToByteArray(responsedata.length);
		//新建一个网络请求包，包含数据的长度，和数据两个部分
		int requestdatasize = lengthdata.length+responsedata.length;
		mDataBuffer = null;
		mDataBuffer = new byte[requestdatasize];
		//数据长度进入请求包
		for (int i = 0; i < lengthdata.length; i++) 
		{
			mDataBuffer[i] = lengthdata[i];
		}
		//数据内容进入请求包
		for (int j = 0; j < responsedata.length; j++) {
			mDataBuffer[j+lengthdata.length] = responsedata[j];  
		}
	}
	
	public void parseData() throws InvalidProtocolBufferException
	{
		//處理第一手数据
		int length = ByteUtil.byteArrayToInt(mDataBuffer, 0);
		if (length+4 != mDataBuffer.length) 
		{
			Log.e(TAG, "网络回包的长度，数据不正确");
			return;
		}
		//Length占四个字节，后面的都是数据
		this.mRespData = null;
		mRespData = new byte[length];
		for (int i = 0; i < mRespData.length; i++) {
			mRespData[i] = mDataBuffer[4+i];
		}
	}
}
