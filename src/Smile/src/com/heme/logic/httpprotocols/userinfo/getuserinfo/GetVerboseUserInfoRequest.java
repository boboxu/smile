package com.heme.logic.httpprotocols.userinfo.getuserinfo;

import java.util.List;

import com.heme.logic.httpprotocols.base.BaseLoginedBusinessRequest;
import com.heme.logic.module.Data.GetVerboseUserInfoReq;

public class GetVerboseUserInfoRequest extends BaseLoginedBusinessRequest {
	public GetVerboseUserInfoRequest(String sessionId, long systemId) {
		super(sessionId, systemId);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setLoginedInfo(String sessionId, long systemId) {
		((GetVerboseUserInfoReq.Builder)mDataBuilder).setSessionId(sessionId);
		((GetVerboseUserInfoReq.Builder)mDataBuilder).setSystemId(systemId);
	}

	@Override
	public void setVersionAndClientType(int version, int clientType) {
		((GetVerboseUserInfoReq.Builder)mDataBuilder).setVersionNo(version);
		((GetVerboseUserInfoReq.Builder)mDataBuilder).setClientType(clientType);
	}

	public void setTargetId(List<Long> targetSystemIdList)
	{
		for (int i = 0; i < targetSystemIdList.size(); i++) {
			((GetVerboseUserInfoReq.Builder)mDataBuilder).addTargetSystemId(targetSystemIdList.get(i));
		}
		super.buildAccessReq(((GetVerboseUserInfoReq.Builder)mDataBuilder).build().toByteString());
	}

	@Override
	public void initmDataBuilder() {
		mDataBuilder = GetVerboseUserInfoReq.newBuilder();
	}
}
