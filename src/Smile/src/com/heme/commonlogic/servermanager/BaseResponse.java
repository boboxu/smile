package com.heme.commonlogic.servermanager;

import java.nio.ByteBuffer;

import android.R.integer;

import com.heme.foundation.error.BaseError;

public class BaseResponse {
	private BaseRequest mRequest;
	private BaseError mError;
	private ByteBuffer mDataBuffer;
	private int mRet;
	
	public final static int RET_SUCCESS = 0;
	public final static int RET_ERROR = -1;
	public int getmRet() {
		return mRet;
	}

	public void setmRet(int mRet) {
		this.mRet = mRet;
	}

	public ByteBuffer getmDataBuffer() {
		return mDataBuffer;
	}

	public void setmDataBuffer(ByteBuffer mDataBuffer) {
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
	
	public void parse()
	{
		//處理第一手数据
	}
}
