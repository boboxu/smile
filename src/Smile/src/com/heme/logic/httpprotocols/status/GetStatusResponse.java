package com.heme.logic.httpprotocols.status;

import com.heme.logic.httpprotocols.base.status.BaseStatusResponse;
import com.heme.logic.module.Status.GetStatusRsp;


public class GetStatusResponse extends BaseStatusResponse {
	GetStatusRsp mGetStatusRsp;

	public GetStatusRsp getmGetGroupInfoRsp() {
		return mGetStatusRsp;
	}

	public void parseData()
			throws com.google.protobuf.InvalidProtocolBufferException {
		super.parseData();
		mGetStatusRsp = mStatusProto.getGetStatusRsp();
	}
}
