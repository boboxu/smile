package com.heme.logic.httpprotocols.userinfo.getuserinfo;

import com.heme.logic.httpprotocols.base.BaseBusinessResponse;
import com.heme.logic.module.Data.GetUserInfoRsp;

public class GetUserInfoResponse extends BaseBusinessResponse {
	GetUserInfoRsp mGetUserInfoRsp;

	public GetUserInfoRsp getmGetGroupInfoRsp() {
		return mGetUserInfoRsp;
	}

	public void parseData()
			throws com.google.protobuf.InvalidProtocolBufferException {
		mGetUserInfoRsp = GetUserInfoRsp.parseFrom(mAccessRespData
				.getBytesBody());
	}
}
