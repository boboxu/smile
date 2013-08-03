package com.heme.logic.httpprotocols.userinfo.getuserinfo;

import java.util.List;

import com.heme.logic.httpprotocols.base.BaseLoginedBusinessRequest;
import com.heme.logic.module.Data.GetUserInfoReq;

public class GetUserInfoRequest extends BaseLoginedBusinessRequest {
	public GetUserInfoRequest(String sessionId, long systemId) {
		super(sessionId, systemId);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setLoginedInfo(String sessionId, long systemId) {
		((GetUserInfoReq.Builder)mDataBuilder).setSessionId(sessionId);
		((GetUserInfoReq.Builder)mDataBuilder).setSystemId(systemId);
	}

	@Override
	public void setVersionAndClientType(String version, int clientType) {
		((GetUserInfoReq.Builder)mDataBuilder).setVersionNo(version);
		((GetUserInfoReq.Builder)mDataBuilder).setClientType(clientType);
	}

	public void setTargetId(List<Long> targetSystemIdList)
	{
		for (int i = 0; i < targetSystemIdList.size(); i++) {
			((GetUserInfoReq.Builder)mDataBuilder).addTargetSystemId(targetSystemIdList.get(i));
		}
		super.buildAccessReq(((GetUserInfoReq.Builder)mDataBuilder).build().toByteString());
	}

	@Override
	public void initmDataBuilder() {
		mDataBuilder = GetUserInfoReq.newBuilder();
	}
}
