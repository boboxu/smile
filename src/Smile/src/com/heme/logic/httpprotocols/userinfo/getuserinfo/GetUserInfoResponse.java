package com.heme.logic.httpprotocols.userinfo.getuserinfo;

import com.heme.logic.httpprotocols.base.business.BaseBusinessResponse;
import com.heme.logic.module.Data.GetUserInfoRsp;

public class GetUserInfoResponse extends BaseBusinessResponse {
	GetUserInfoRsp mGetUserInfoRsp;

	public GetUserInfoRsp getmGetUserInfoRsp() {
		return mGetUserInfoRsp;
	}

	public void parseData()
			throws com.google.protobuf.InvalidProtocolBufferException {
		super.parseData();
		mGetUserInfoRsp = mDataSvrProto.getGetUserInfoRspInfo();
	}
}
