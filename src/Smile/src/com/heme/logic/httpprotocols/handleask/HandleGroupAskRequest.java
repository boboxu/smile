package com.heme.logic.httpprotocols.handleask;

import com.heme.logic.httpprotocols.base.BaseLoginedBusinessRequest;
import com.heme.logic.module.Data.ApplySocialGroupReq;

public class HandleGroupAskRequest extends BaseLoginedBusinessRequest {
	// 只有社区群才有这个同意加入群的功能

	ApplySocialGroupReq.Builder mApplySocialGroupReq = ApplySocialGroupReq
			.newBuilder();

	@Override
	public void setLoginedInfo(String sessionId, long systemId) {
		mApplySocialGroupReq.setSessionId(sessionId);
		mApplySocialGroupReq.setSystemId(systemId);
		super.setBody(mApplySocialGroupReq.build().toByteString());
	}

	@Override
	public void setVersionAndClientType(int version, int clientType) {
		mApplySocialGroupReq.setVersionNo(version);
		mApplySocialGroupReq.setClientType(clientType);
		super.setBody(mApplySocialGroupReq.build().toByteString());
	}

	public void setTargetId(int groupId) {
		mApplySocialGroupReq.setGroupId(groupId);
		super.setBody(mApplySocialGroupReq.build().toByteString());
	}

}
