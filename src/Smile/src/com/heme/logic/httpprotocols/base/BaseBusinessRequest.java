package com.heme.logic.httpprotocols.base;

import com.heme.smile.common.Configuration;



public abstract class BaseBusinessRequest extends BasePbRequest{
	
	protected static final int PROTO_VERSION = 1;
	protected static int CLIENT_TYPE = Configuration.APP_VERSION;
	public BaseBusinessRequest()
	{
		super();
		setVersionAndClientType(PROTO_VERSION,CLIENT_TYPE);
	}

	public abstract void setVersionAndClientType(int version,int clientType);
}
