package com.heme.logic.httpprotocols.userinfo.updateuserinfo;

import com.heme.logic.httpprotocols.base.business.BaseBusinessResponse;
import com.heme.logic.module.Data.SetUserIconRsp;

public class UpdateUserIconResponse extends BaseBusinessResponse {
	SetUserIconRsp mSetUserIconRsp;

	public SetUserIconRsp getmGetGroupInfoRsp() {
		return mSetUserIconRsp;
	}

	public void parseData()
			throws com.google.protobuf.InvalidProtocolBufferException {
		mSetUserIconRsp = mDataSvrProto.getSetUserIconRspInfo();
	}
}
