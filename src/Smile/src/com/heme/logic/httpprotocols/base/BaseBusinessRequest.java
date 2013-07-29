package com.heme.logic.httpprotocols.base;

import com.heme.logic.common.Configuration;
import com.heme.logic.module.Data.LoginReq.Builder;



public abstract class BaseBusinessRequest extends BasePbRequest{
	
	protected static final int PROTO_VERSION = 1;
	protected static int CLIENT_TYPE = Configuration.APP_VERSION;
	protected com.google.protobuf.GeneratedMessage.Builder<Builder> mDataBuilder;
	public BaseBusinessRequest()
	{
		super();
		initmDataBuilder();
		setVersionAndClientType(PROTO_VERSION,CLIENT_TYPE);
	}

	public abstract void setVersionAndClientType(int version,int clientType);
	public abstract void initmDataBuilder();
}
