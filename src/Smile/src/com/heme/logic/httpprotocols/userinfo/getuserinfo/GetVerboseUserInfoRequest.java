package com.heme.logic.httpprotocols.userinfo.getuserinfo;

import java.util.List;

import com.heme.logic.httpprotocols.base.business.BaseLoginedBusinessRequest;
import com.heme.logic.module.Data.DataSvrProto.Cmd;
import com.heme.logic.module.Data.GetVerboseUserInfoReq;

public class GetVerboseUserInfoRequest extends BaseLoginedBusinessRequest {

	GetVerboseUserInfoReq.Builder mGetVerboseUserInfoReqBuilder;

	public GetVerboseUserInfoRequest(String sessionId, long systemId) {
		super(sessionId, systemId);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setLoginedInfo(String sessionId, long systemId) {
		mGetVerboseUserInfoReqBuilder.setSessionId(sessionId);
		mGetVerboseUserInfoReqBuilder.setSystemId(systemId);
	}

	@Override
	public void setVersionAndClientType(String version, int clientType) {
		mGetVerboseUserInfoReqBuilder.setVersionNo(version);
		mGetVerboseUserInfoReqBuilder.setClientType(clientType);
	}

	public void setTargetId(List<Long> targetSystemIdList) {
		mGetVerboseUserInfoReqBuilder.addAllTargetSystemId(targetSystemIdList);
		mDataSvrProtoBuilder
				.setGetVerboseUserInfoReqInfo(mGetVerboseUserInfoReqBuilder
						.build());
		mDataSvrProtoBuilder.setEnumCmd(Cmd.GetVerboseUserInfo);
		super.setBody(mDataSvrProtoBuilder.build().toByteString());
	}

	@Override
	public void initmDataBuilder() {
		mGetVerboseUserInfoReqBuilder = GetVerboseUserInfoReq.newBuilder();
	}
}
