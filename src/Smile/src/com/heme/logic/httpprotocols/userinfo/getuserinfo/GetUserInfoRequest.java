package com.heme.logic.httpprotocols.userinfo.getuserinfo;

import java.util.List;

import com.heme.logic.httpprotocols.base.BaseLoginedBusinessRequest;
import com.heme.logic.module.Data.GetUserInfoReq;

public class GetUserInfoRequest extends BaseLoginedBusinessRequest {
	GetUserInfoReq.Builder mGetUserInfoReqBuilder = GetUserInfoReq.newBuilder();
	@Override
	public void setLoginedInfo(String sessionId, long systemId) {
		mGetUserInfoReqBuilder.setSessionId(sessionId);
		mGetUserInfoReqBuilder.setSystemId(systemId);
		super.setBody(mGetUserInfoReqBuilder.build().toByteString());
	}

	@Override
	public void setVersionAndClientType(int version, int clientType) {
		mGetUserInfoReqBuilder.setVersionNo(version);
		mGetUserInfoReqBuilder.setClientType(clientType);
		super.setBody(mGetUserInfoReqBuilder.build().toByteString());
	}

	public void setTargetId(List<Long> targetSystemIdList)
	{
		for (int i = 0; i < targetSystemIdList.size(); i++) {
			mGetUserInfoReqBuilder.addTargetSystemId(targetSystemIdList.get(i));
		}
		super.setBody(mGetUserInfoReqBuilder.build().toByteString());
	}

	@Override
	public void initmDataBuilder() {
		// TODO Auto-generated method stub
		
	}
}
