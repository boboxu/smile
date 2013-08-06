package com.heme.logic.httpprotocols.userinfo.updateuserinfo;

import com.heme.logic.httpprotocols.base.BaseBusinessResponse;
import com.heme.logic.module.Data.SetSelfInfoRsp;

public class UpdateUserInfoResponse extends BaseBusinessResponse {
	SetSelfInfoRsp mSetSelfInfoRsp;

	public SetSelfInfoRsp getmGetGroupInfoRsp() {
		return mSetSelfInfoRsp;
	}

	public void parseData()
			throws com.google.protobuf.InvalidProtocolBufferException {
		mSetSelfInfoRsp = SetSelfInfoRsp.parseFrom(mTransData
				.getBytesBody());
	}
}
