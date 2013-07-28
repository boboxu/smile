package com.heme.logic.httpprotocols.userinfo.getuserinfo;

import com.heme.logic.httpprotocols.base.BaseBusinessResponse;
import com.heme.logic.module.Data.GetVerboseUserInfoRsp;

public class GetVerboseUserInfoResponse extends BaseBusinessResponse {
	GetVerboseUserInfoRsp mGetVerboseUserInfoRsp;

	public GetVerboseUserInfoRsp getmGetGroupInfoRsp() {
		return mGetVerboseUserInfoRsp;
	}

	public void parseData()
			throws com.google.protobuf.InvalidProtocolBufferException {
		mGetVerboseUserInfoRsp = GetVerboseUserInfoRsp.parseFrom(mAccessRespData
				.getBytesBody());
	}
}
