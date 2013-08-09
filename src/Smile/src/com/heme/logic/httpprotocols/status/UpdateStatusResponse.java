package com.heme.logic.httpprotocols.status;

import com.heme.logic.httpprotocols.base.status.BaseStatusResponse;
import com.heme.logic.module.Status.SetStatusRsp;


public class UpdateStatusResponse extends BaseStatusResponse {
	SetStatusRsp mSetStatusRsp;

	public SetStatusRsp getmGetGroupInfoRsp() {
		return mSetStatusRsp;
	}

	public void parseData()
			throws com.google.protobuf.InvalidProtocolBufferException {
		mSetStatusRsp = mStatusProto.getSetStatusRsp();
	}
}
