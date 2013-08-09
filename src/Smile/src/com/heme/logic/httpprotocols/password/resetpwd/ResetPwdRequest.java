package com.heme.logic.httpprotocols.password.resetpwd;

import com.heme.logic.httpprotocols.base.business.BaseBusinessRequest;
import com.heme.logic.module.Data.FindPasswdVerifyReq;
import com.heme.logic.module.Data.DataSvrProto.Cmd;

public class ResetPwdRequest extends BaseBusinessRequest {

	FindPasswdVerifyReq.Builder mFindPasswdVerifyReqBuilder;
	@Override
	public void setVersionAndClientType(String version, int clientType) {
		mFindPasswdVerifyReqBuilder.setVersionNo(version);
		mFindPasswdVerifyReqBuilder.setClientType(clientType);
	}

	public void setVerifyCode(String verifyCode)
	{
		mFindPasswdVerifyReqBuilder.setVerifyCode(verifyCode);
		mDataSvrProtoBuilder.setFindPasswdVerifyReqInfo(mFindPasswdVerifyReqBuilder.build());
		mDataSvrProtoBuilder.setEnumCmd(Cmd.FindPasswdVerify);
		super.setBody(mDataSvrProtoBuilder.build().toByteString());
	}

	@Override
	public void initmDataBuilder() {
		mFindPasswdVerifyReqBuilder = FindPasswdVerifyReq.newBuilder();
	}

}
