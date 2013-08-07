package com.heme.logic.httpprotocols.base;

import com.heme.commonlogic.servermanager.BasePbRequest;
import com.heme.logic.common.Configuration;
import com.heme.logic.module.Data.DataSvrProto;

public abstract class BaseBusinessRequest extends BasePbRequest {

	protected static final String PROTO_VERSION = "1";
	protected static int CLIENT_TYPE = Configuration.APP_VERSION;
	protected com.google.protobuf.GeneratedMessage.Builder<?> mDataBuilder;
	protected DataSvrProto.Builder mDataSvrBuilder;
	public BaseBusinessRequest() {
		super();
		mDataSvrBuilder = DataSvrProto.newBuilder();
		initmDataBuilder();
		setVersionAndClientType(PROTO_VERSION, CLIENT_TYPE);
	}

	public abstract void setVersionAndClientType(String version, int clientType);

	public abstract void initmDataBuilder();
	
	@Override
	protected void setCmd() {
		mTransDataBuilder.setStrCmd("DataSvr");
	}
}
