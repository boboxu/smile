package com.heme.logic.httpprotocols.password.resetpwd;

import com.heme.logic.httpprotocols.base.BaseBusinessRequest;
import com.heme.logic.module.Data.FindPasswdVerifyReq;

public class ResetPwdRequest extends BaseBusinessRequest {

	@Override
	public void setVersionAndClientType(String version, int clientType) {
		((FindPasswdVerifyReq.Builder)mDataBuilder).setVersionNo(version);
		((FindPasswdVerifyReq.Builder)mDataBuilder).setClientType(clientType);
	}

	public void setVerifyCode(String verifyCode)
	{
		((FindPasswdVerifyReq.Builder)mDataBuilder).setVerifyCode(verifyCode);
		super.buildAccessReq(((FindPasswdVerifyReq.Builder)mDataBuilder).build().toByteString());
	}

	@Override
	public void initmDataBuilder() {
		mDataBuilder = FindPasswdVerifyReq.newBuilder();
	}

}
