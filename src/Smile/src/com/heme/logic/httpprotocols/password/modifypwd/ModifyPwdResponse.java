package com.heme.logic.httpprotocols.password.modifypwd;

import com.heme.logic.httpprotocols.base.business.BaseBusinessResponse;
import com.heme.logic.module.Data.SetPasswdRsp;

public class ModifyPwdResponse extends BaseBusinessResponse {
	SetPasswdRsp mSetPasswdRsp;

	public SetPasswdRsp getmGetGroupInfoRsp() {
		return mSetPasswdRsp;
	}

	public void parseData()
			throws com.google.protobuf.InvalidProtocolBufferException {
		mSetPasswdRsp = mDataSvrProto.getSetPasswdRspInfo();
	}
}
