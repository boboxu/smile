package com.heme.logic.httpprotocols.password.modifypwd;

import com.heme.logic.httpprotocols.base.BaseBusinessRequest;
import com.heme.logic.module.Data.SetPasswdReq;

public class ModifyPwdRequest extends BaseBusinessRequest {

	SetPasswdReq.Builder mSetPasswdReqBuilder = SetPasswdReq.newBuilder();

	@Override
	public void setVersionAndClientType(int version, int clientType) {
		mSetPasswdReqBuilder.setVersionNo(version);
		mSetPasswdReqBuilder.setClientType(clientType);
		super.setBody(mSetPasswdReqBuilder.build().toByteString());
	}

	public void setPassword(String newPassword,String oldPassword)
	{
		mSetPasswdReqBuilder.setNewPasswd(newPassword);
		mSetPasswdReqBuilder.setOldPasswd(oldPassword);
		super.setBody(mSetPasswdReqBuilder.build().toByteString());
	}

	@Override
	public void initmDataBuilder() {
		// TODO Auto-generated method stub
		
	}

}
