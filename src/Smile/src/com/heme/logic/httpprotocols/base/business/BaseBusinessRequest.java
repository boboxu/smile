package com.heme.logic.httpprotocols.base.business;

import com.heme.commonlogic.servermanager.BasePbRequest;
import com.heme.logic.common.Configuration;
import com.heme.logic.module.Data.DataSvrProto;

public abstract class BaseBusinessRequest extends BasePbRequest {

	protected DataSvrProto.Builder mDataSvrProtoBuilder;
	public BaseBusinessRequest() {
		super();
		mDataSvrProtoBuilder = DataSvrProto.newBuilder();
		initmDataBuilder();
		setVersionAndClientType(Configuration.PROTO_VERSION, Configuration.APP_VERSION);
	}

	public abstract void setVersionAndClientType(String version, int clientType);

	public abstract void initmDataBuilder();
	
	@Override
	protected void setCmd() {
		mTransDataBuilder.setStrCmd("DataSvr");
	}
}
