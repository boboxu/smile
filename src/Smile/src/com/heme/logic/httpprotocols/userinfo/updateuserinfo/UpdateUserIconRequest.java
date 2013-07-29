package com.heme.logic.httpprotocols.userinfo.updateuserinfo;

import com.heme.logic.httpprotocols.base.BaseLoginedBusinessRequest;
import com.heme.logic.module.Data.SetUserIconReq;

public class UpdateUserIconRequest extends BaseLoginedBusinessRequest {

	SetUserIconReq.Builder mSetUserIconReqBuilder = SetUserIconReq.newBuilder();
	@Override
	public void setLoginedInfo(String sessionId, long systemId) {
		mSetUserIconReqBuilder.setSessionId(sessionId);
		mSetUserIconReqBuilder.setSystemId(systemId);
		super.setBody(mSetUserIconReqBuilder.build().toByteString());

	}

	@Override
	public void setVersionAndClientType(int version, int clientType) {

		mSetUserIconReqBuilder.setVersionNo(version);
		mSetUserIconReqBuilder.setClientType(clientType);
		super.setBody(mSetUserIconReqBuilder.build().toByteString());

	}

	public void setIconName(String name)
	{
		mSetUserIconReqBuilder.setIconName(name);
		super.setBody(mSetUserIconReqBuilder.build().toByteString());
	}

	@Override
	public void initmDataBuilder() {
		// TODO Auto-generated method stub
		
	}
}
