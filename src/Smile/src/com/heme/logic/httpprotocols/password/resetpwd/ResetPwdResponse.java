package com.heme.logic.httpprotocols.password.resetpwd;

import com.heme.logic.httpprotocols.base.business.BaseBusinessResponse;
import com.heme.logic.module.Data.FindPasswdVerifyRsp;

public class ResetPwdResponse extends BaseBusinessResponse {
	FindPasswdVerifyRsp mFindPasswdVerifyRsp;

	public FindPasswdVerifyRsp getmGetGroupInfoRsp() {
		return mFindPasswdVerifyRsp;
	}

	public void parseData()
			throws com.google.protobuf.InvalidProtocolBufferException {
		mFindPasswdVerifyRsp = mDataSvrProto.getFindPasswdVerifyRspInfo();
	}
}
