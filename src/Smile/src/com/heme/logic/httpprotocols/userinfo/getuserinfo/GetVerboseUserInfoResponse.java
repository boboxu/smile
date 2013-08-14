package com.heme.logic.httpprotocols.userinfo.getuserinfo;

import com.heme.logic.httpprotocols.base.business.BaseBusinessResponse;
import com.heme.logic.module.Data.GetVerboseUserInfoRsp;

public class GetVerboseUserInfoResponse extends BaseBusinessResponse {
	GetVerboseUserInfoRsp mGetVerboseUserInfoRsp;

	public GetVerboseUserInfoRsp getmGetVerboseInfoRsp() {
		return mGetVerboseUserInfoRsp;
	}

	public void parseData()
			throws com.google.protobuf.InvalidProtocolBufferException {
		super.parseData();
		mGetVerboseUserInfoRsp = mDataSvrProto.getGetVerboseUserInfoRspInfo();
	}
}
