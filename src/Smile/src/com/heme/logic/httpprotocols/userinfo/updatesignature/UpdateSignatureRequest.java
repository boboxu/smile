package com.heme.logic.httpprotocols.userinfo.updatesignature;

import com.heme.logic.httpprotocols.base.BaseLoginedBusinessRequest;
import com.heme.logic.module.Data.SetSignatureReq;

public class UpdateSignatureRequest extends BaseLoginedBusinessRequest {

	public UpdateSignatureRequest(String sessionId, long systemId) {
		super(sessionId, systemId);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setLoginedInfo(String sessionId, long systemId) {
		((SetSignatureReq.Builder)mDataBuilder).setSessionId(sessionId);
		((SetSignatureReq.Builder)mDataBuilder).setSystemId(systemId);
	}

	@Override
	public void setVersionAndClientType(String version, int clientType) {
		((SetSignatureReq.Builder)mDataBuilder).setVersionNo(version);
		((SetSignatureReq.Builder)mDataBuilder).setClientType(clientType);
	}

	public void setSignature(String signature)
	{
		((SetSignatureReq.Builder)mDataBuilder).setSignature(signature);
		super.setBody(((SetSignatureReq.Builder)mDataBuilder).build().toByteString());
	}

	@Override
	public void initmDataBuilder() {
		mDataBuilder = SetSignatureReq.newBuilder();
	}
}
