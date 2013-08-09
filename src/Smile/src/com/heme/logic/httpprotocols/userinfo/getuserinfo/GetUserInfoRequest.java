package com.heme.logic.httpprotocols.userinfo.getuserinfo;

import java.util.List;

import com.heme.logic.httpprotocols.base.business.BaseLoginedBusinessRequest;
import com.heme.logic.module.Data.GetUserInfoReq;
import com.heme.logic.module.Data.DataSvrProto.Cmd;

public class GetUserInfoRequest extends BaseLoginedBusinessRequest {
	
	GetUserInfoReq.Builder mGetUserInfoReqBuilder;
	public GetUserInfoRequest(String sessionId, long systemId) {
		super(sessionId, systemId);
	}

	@Override
	public void setLoginedInfo(String sessionId, long systemId) {
		mGetUserInfoReqBuilder.setSessionId(sessionId);
		mGetUserInfoReqBuilder.setSystemId(systemId);
	}

	@Override
	public void setVersionAndClientType(String version, int clientType) {
		mGetUserInfoReqBuilder.setVersionNo(version);
		mGetUserInfoReqBuilder.setClientType(clientType);
	}

	public void setTargetId(List<Long> targetSystemIdList)
	{
		mGetUserInfoReqBuilder.addAllTargetSystemId(targetSystemIdList);
		mDataSvrProtoBuilder.setEnumCmd(Cmd.GetUserInfo);
		mDataSvrProtoBuilder.setGetUserInfoReqInfo(mGetUserInfoReqBuilder.build());
		super.setBody(mGetUserInfoReqBuilder.build().toByteString());
	}

	@Override
	public void initmDataBuilder() {
		mGetUserInfoReqBuilder = GetUserInfoReq.newBuilder();
	}
}
