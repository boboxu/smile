package com.heme.logic.httpprotocols.userinfo.updatesignature;

import com.heme.logic.httpprotocols.base.BaseLoginedBusinessRequest;
import com.heme.logic.module.Data.SetSignatureReq;

public class UpdateSignatureRequest extends BaseLoginedBusinessRequest {

	SetSignatureReq.Builder mSetSignatureReqBuilder = SetSignatureReq.newBuilder();
	@Override
	public void setLoginedInfo(String sessionId, long systemId) {
		mSetSignatureReqBuilder.setSessionId(sessionId);
		mSetSignatureReqBuilder.setSystemId(systemId);
		super.setBody(mSetSignatureReqBuilder.build().toByteString());
	}

	@Override
	public void setVersionAndClientType(int version, int clientType) {
		mSetSignatureReqBuilder.setVersionNo(version);
		mSetSignatureReqBuilder.setClientType(clientType);
		super.setBody(mSetSignatureReqBuilder.build().toByteString());
	}

	public void setSignature(String signature)
	{
		mSetSignatureReqBuilder.setSignature(signature);
		super.setBody(mSetSignatureReqBuilder.build().toByteString());
	}
}
