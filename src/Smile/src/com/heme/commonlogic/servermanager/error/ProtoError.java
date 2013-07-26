package com.heme.commonlogic.servermanager.error;

import com.heme.foundation.error.BaseError;

public class ProtoError extends BaseError {
	
	public ProtoError(int errcode, String msg) {
		super(errcode, msg);
	
	}

	public ProtoError(int errcode)
	{
		super(errcode, "");
	}
	public static int ERRCODE_NORESPONSE_CONTENT = 1;
	public static int ERRCODE_ERRRESPONSENAME = 2;
	public static int ERRCODE_INVALID_PROTOBUFFER = 3;
}
