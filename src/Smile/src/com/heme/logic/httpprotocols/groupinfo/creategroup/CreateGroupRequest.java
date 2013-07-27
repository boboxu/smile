package com.heme.logic.httpprotocols.groupinfo.creategroup;

import com.heme.logic.httpprotocols.base.BaseLoginedBusinessRequest;
import com.heme.logic.module.Data.CreateFixedGroupReq;

public class CreateGroupRequest extends BaseLoginedBusinessRequest {

	public CreateFixedGroupReq.Builder mCreateFixedGroupReqBuilder = CreateFixedGroupReq.newBuilder();
	@Override
	public void setLoginedInfo(String sessionId, long systemId) {
		
	}

	@Override
	public void setVersionAndClientType(int version, int clientType) {
		// TODO Auto-generated method stub
		
	}
	//社区群、永久群、临时群（讨论组）
}
