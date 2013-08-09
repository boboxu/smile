package com.heme.logic.httpprotocols.handleask;

import com.heme.logic.httpprotocols.base.business.BaseLoginedBusinessRequest;

public class HandleGroupAskRequest extends BaseLoginedBusinessRequest {
	// 只有社区群才有这个同意加入群的功能

	public HandleGroupAskRequest(String sessionId, long systemId) {
		super(sessionId, systemId);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setLoginedInfo(String sessionId, long systemId) {
//		((ApplySocialGroupReq.Builder)mDataBuilder).setSessionId(sessionId);
//		((ApplySocialGroupReq.Builder)mDataBuilder).setSystemId(systemId);
	}

	@Override
	public void setVersionAndClientType(String version, int clientType) {
//		((ApplySocialGroupReq.Builder)mDataBuilder).setVersionNo(version);
//		((ApplySocialGroupReq.Builder)mDataBuilder).setClientType(clientType);
	}

	public void setTargetId(int groupId) {
//		((ApplySocialGroupReq.Builder)mDataBuilder).setGroupId(groupId);
//		super.buildAccessReq(((ApplySocialGroupReq.Builder)mDataBuilder).build().toByteString());
	}

	@Override
	public void initmDataBuilder() {
//		mDataBuilder = ApplySocialGroupReq.newBuilder();
	}

}
