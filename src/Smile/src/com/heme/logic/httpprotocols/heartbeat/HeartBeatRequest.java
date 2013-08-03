package com.heme.logic.httpprotocols.heartbeat;

import com.heme.logic.httpprotocols.base.BaseBusinessRequest;
import com.heme.logic.module.Data.HbReq;

public class HeartBeatRequest extends BaseBusinessRequest {

	@Override
	public void setVersionAndClientType(String version, int clientType) {
		((HbReq.Builder) mDataBuilder).setVersionNo(version);
		((HbReq.Builder) mDataBuilder).setClientType(clientType);
	}

	@Override
	public void initmDataBuilder() {
		mDataBuilder = HbReq.newBuilder();
	}

	public HeartBeatRequest() {
		super.buildAccessReq(((HbReq.Builder) mDataBuilder).build()
				.toByteString());
	}
}
