package com.heme.logic.httpprotocols.password.resetpwd;

import com.heme.logic.httpprotocols.base.BaseBusinessRequest;
import com.heme.logic.httpprotocols.updatestatus.UpdateStatusRequest.USERSTATUS;
import com.heme.logic.module.Data.FindPasswdVerifyReq;

public class ResetPwdRequest extends BaseBusinessRequest {

	FindPasswdVerifyReq.Builder mFindPasswdVerifyReqBuilder = FindPasswdVerifyReq.newBuilder();

	@Override
	public void setVersionAndClientType(int version, int clientType) {
		mFindPasswdVerifyReqBuilder.setVersionNo(version);
		mFindPasswdVerifyReqBuilder.setClientType(clientType);
		super.setBody(mFindPasswdVerifyReqBuilder.build().toByteString());
	}

	public void setVerifyCode(String verifyCode)
	{
		mFindPasswdVerifyReqBuilder.setVerifyCode(verifyCode);
		super.setBody(mFindPasswdVerifyReqBuilder.build().toByteString());
	}

	@Override
	public void initmDataBuilder() {
		// TODO Auto-generated method stub
		
	}

}
