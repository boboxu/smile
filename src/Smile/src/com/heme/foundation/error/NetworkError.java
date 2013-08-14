package com.heme.foundation.error;

public class NetworkError extends BaseError {
	public NetworkError(int errConnectFailed, String string) {
		super(errConnectFailed,string);
	}
	public static final int ERR_CONNECT_TIMEOUT = 3;
	public static final int ERR_CONNECT_FAILED = 4;
	
	
}
