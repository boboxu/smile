package com.heme.logic.httpprotocols.password.modifypwd;

import com.heme.logic.httpprotocols.base.business.BaseLoginedBusinessRequest;
import com.heme.logic.module.Data.SetPasswdReq;
import com.heme.logic.module.Data.DataSvrProto.Cmd;

public class ModifyPwdRequest extends BaseLoginedBusinessRequest {

	SetPasswdReq.Builder mSetPasswdReqBuilder;
	public ModifyPwdRequest(String sessionId, long systemId) {
		super(sessionId, systemId);
		
	}

	@Override
	public void setVersionAndClientType(String version, int clientType) {
		mSetPasswdReqBuilder.setVersionNo(version);
		mSetPasswdReqBuilder.setClientType(clientType);
	}

	public void setPassword(String newPassword,String oldPassword)
	{
		mSetPasswdReqBuilder.setNewPasswd(newPassword);
		mSetPasswdReqBuilder.setOldPasswd(oldPassword);
		mDataSvrProtoBuilder.setEnumCmd(Cmd.SetPasswd);
		mDataSvrProtoBuilder.setSetPasswdReqInfo(mSetPasswdReqBuilder.build());
		super.setBody(mSetPasswdReqBuilder.build().toByteString());
	}

	@Override
	public void initmDataBuilder() {
		mSetPasswdReqBuilder = SetPasswdReq.newBuilder();
	}

	@Override
	protected void setLoginedInfo(String sessionId, long systemId) {
		mSetPasswdReqBuilder.setSessionId(sessionId);
		mSetPasswdReqBuilder.setSystemId(systemId);
	}

}
