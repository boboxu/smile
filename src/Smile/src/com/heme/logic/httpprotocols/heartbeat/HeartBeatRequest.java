package com.heme.logic.httpprotocols.heartbeat;

import com.heme.logic.httpprotocols.base.BaseBusinessRequest;
import com.heme.logic.module.Data.HbReq;

public class HeartBeatRequest extends BaseBusinessRequest {

	HbReq.Builder mSetStatusReqBuilder = HbReq.newBuilder();

	@Override
	public void setVersionAndClientType(int version, int clientType) {
		mSetStatusReqBuilder.setVersionNo(version);
		mSetStatusReqBuilder.setClientType(clientType);
		super.setBody(mSetStatusReqBuilder.build().toByteString());
	}
}
