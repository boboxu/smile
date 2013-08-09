package com.heme.logic.httpprotocols.userinfo.updatesignature;

import com.heme.logic.httpprotocols.base.business.BaseLoginedBusinessRequest;
import com.heme.logic.module.Data.SetSignatureReq;
import com.heme.logic.module.Data.DataSvrProto.Cmd;

public class UpdateSignatureRequest extends BaseLoginedBusinessRequest {

	SetSignatureReq.Builder mSetSignatureReqBuilder;
	public UpdateSignatureRequest(String sessionId, long systemId) {
		super(sessionId, systemId);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setLoginedInfo(String sessionId, long systemId) {
		mSetSignatureReqBuilder.setSessionId(sessionId);
		mSetSignatureReqBuilder.setSystemId(systemId);
	}

	@Override
	public void setVersionAndClientType(String version, int clientType) {
		mSetSignatureReqBuilder.setVersionNo(version);
		mSetSignatureReqBuilder.setClientType(clientType);
	}

	public void setSignature(String signature)
	{
		mSetSignatureReqBuilder.setSignature(signature);
		mDataSvrProtoBuilder.setEnumCmd(Cmd.SetSignature);
		mDataSvrProtoBuilder.setSetSignatureReqInfo(mSetSignatureReqBuilder.build());
		super.setBody(mDataSvrProtoBuilder.build().toByteString());
	}

	@Override
	public void initmDataBuilder() {
		mSetSignatureReqBuilder = SetSignatureReq.newBuilder();
	}
}
