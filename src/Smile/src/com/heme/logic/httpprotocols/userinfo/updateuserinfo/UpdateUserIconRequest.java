package com.heme.logic.httpprotocols.userinfo.updateuserinfo;

import com.heme.logic.httpprotocols.base.BaseLoginedBusinessRequest;
import com.heme.logic.module.Data.SetUserIconReq;

public class UpdateUserIconRequest extends BaseLoginedBusinessRequest {

	@Override
	public void setLoginedInfo(String sessionId, long systemId) {
		((SetUserIconReq.Builder)mDataBuilder).setSessionId(sessionId);
		((SetUserIconReq.Builder)mDataBuilder).setSystemId(systemId);
	}

	@Override
	public void setVersionAndClientType(int version, int clientType) {
		((SetUserIconReq.Builder)mDataBuilder).setVersionNo(version);
		((SetUserIconReq.Builder)mDataBuilder).setClientType(clientType);
	}

	public void setIconName(String name)
	{
		((SetUserIconReq.Builder)mDataBuilder).setIconName(name);
		super.buildAccessReq(((SetUserIconReq.Builder)mDataBuilder).build().toByteString());
	}

	@Override
	public void initmDataBuilder() {
		mDataBuilder = SetUserIconReq.newBuilder();
	}
}
