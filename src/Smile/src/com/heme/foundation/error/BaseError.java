package com.heme.foundation.error;


public class BaseError {
	int mErrcode;
	String mErrMsg;
	
	public BaseError(int errcode,String msg)
	{
		super();
		mErrcode = errcode;
		mErrMsg = msg;
	}
	
	
}
