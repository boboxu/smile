package com.heme.logic.httpprotocols.password.modifypwd;

import com.heme.logic.httpprotocols.base.BaseBusinessRequest;
import com.heme.logic.module.Data.SetPasswdReq;

public class ModifyPwdRequest extends BaseBusinessRequest {

	@Override
	public void setVersionAndClientType(int version, int clientType) {
		((SetPasswdReq.Builder)mDataBuilder).setVersionNo(version);
		((SetPasswdReq.Builder)mDataBuilder).setClientType(clientType);
	}

	public void setPassword(String newPassword,String oldPassword)
	{
		((SetPasswdReq.Builder)mDataBuilder).setNewPasswd(newPassword);
		((SetPasswdReq.Builder)mDataBuilder).setOldPasswd(oldPassword);
		super.buildAccessReq(((SetPasswdReq.Builder)mDataBuilder).build().toByteString());
	}

	@Override
	public void initmDataBuilder() {
		mDataBuilder = SetPasswdReq.newBuilder();
	}

}
