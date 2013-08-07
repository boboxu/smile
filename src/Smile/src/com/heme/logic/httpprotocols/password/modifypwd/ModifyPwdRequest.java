package com.heme.logic.httpprotocols.password.modifypwd;

import com.heme.logic.httpprotocols.base.BaseLoginedBusinessRequest;
import com.heme.logic.module.Data.SetPasswdReq;

public class ModifyPwdRequest extends BaseLoginedBusinessRequest {

	public ModifyPwdRequest(String sessionId, long systemId) {
		super(sessionId, systemId);
		
	}

	@Override
	public void setVersionAndClientType(String version, int clientType) {
		((SetPasswdReq.Builder)mDataBuilder).setVersionNo(version);
		((SetPasswdReq.Builder)mDataBuilder).setClientType(clientType);
	}

	public void setPassword(String newPassword,String oldPassword)
	{
		((SetPasswdReq.Builder)mDataBuilder).setNewPasswd(newPassword);
		((SetPasswdReq.Builder)mDataBuilder).setOldPasswd(oldPassword);
		super.setBody(((SetPasswdReq.Builder)mDataBuilder).build().toByteString());
	}

	@Override
	public void initmDataBuilder() {
		mDataBuilder = SetPasswdReq.newBuilder();
	}

	@Override
	protected void setLoginedInfo(String sessionId, long systemId) {
		((SetPasswdReq.Builder)mDataBuilder).setSessionId(sessionId);
		((SetPasswdReq.Builder)mDataBuilder).setSystemId(systemId);
	}

}
