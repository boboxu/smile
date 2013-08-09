package com.heme.logic.httpprotocols.userinfo.updateuserinfo;

import com.heme.logic.httpprotocols.base.business.BaseLoginedBusinessRequest;
import com.heme.logic.module.Data.SetUserIconReq;
import com.heme.logic.module.Data.DataSvrProto.Cmd;

public class UpdateUserIconRequest extends BaseLoginedBusinessRequest {

	SetUserIconReq.Builder mSetUserIconReqBuilder;

	public UpdateUserIconRequest(String sessionId, long systemId) {
		super(sessionId, systemId);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setLoginedInfo(String sessionId, long systemId) {
		mSetUserIconReqBuilder.setSessionId(sessionId);
		mSetUserIconReqBuilder.setSystemId(systemId);
	}

	@Override
	public void setVersionAndClientType(String version, int clientType) {
		mSetUserIconReqBuilder.setVersionNo(version);
		mSetUserIconReqBuilder.setClientType(clientType);
	}

	public void setIconName(String name) {
		mSetUserIconReqBuilder.setIconName(name);
		mDataSvrProtoBuilder.setEnumCmd(Cmd.SetUserIcon);
		mDataSvrProtoBuilder.setSetUserIconReqInfo(mSetUserIconReqBuilder.build());
		super.setBody(mDataSvrProtoBuilder.build().toByteString());
	}

	@Override
	public void initmDataBuilder() {
		mSetUserIconReqBuilder = SetUserIconReq.newBuilder();
	}
}
