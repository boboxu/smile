package com.heme.logic.httpprotocols.userinfo.getuserinfo;

import java.util.List;

import com.heme.logic.httpprotocols.base.BaseLoginedBusinessRequest;
import com.heme.logic.module.Data.GetVerboseUserInfoReq;

public class GetVerboseUserInfoRequest extends BaseLoginedBusinessRequest {
	GetVerboseUserInfoReq.Builder mGetVerboseUserInfoReqBuilder = GetVerboseUserInfoReq.newBuilder();
	@Override
	public void setLoginedInfo(String sessionId, long systemId) {
		mGetVerboseUserInfoReqBuilder.setSessionId(sessionId);
		mGetVerboseUserInfoReqBuilder.setSystemId(systemId);
		super.setBody(mGetVerboseUserInfoReqBuilder.build().toByteString());
	}

	@Override
	public void setVersionAndClientType(int version, int clientType) {
		mGetVerboseUserInfoReqBuilder.setVersionNo(version);
		mGetVerboseUserInfoReqBuilder.setClientType(clientType);
		super.setBody(mGetVerboseUserInfoReqBuilder.build().toByteString());
	}

	public void setTargetId(List<Long> targetSystemIdList)
	{
		for (int i = 0; i < targetSystemIdList.size(); i++) {
			mGetVerboseUserInfoReqBuilder.addTargetSystemId(targetSystemIdList.get(i));
		}
		super.setBody(mGetVerboseUserInfoReqBuilder.build().toByteString());
	}
}
