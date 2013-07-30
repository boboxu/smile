package com.heme.logic.httpprotocols.groupinfo.deletegroup;

import com.heme.logic.httpprotocols.base.BaseLoginedBusinessRequest;
import com.heme.logic.module.Data.DelGroupReq;

public class DeleteGroupRequest extends BaseLoginedBusinessRequest {
	//删除群

	public DeleteGroupRequest(String sessionId, long systemId) {
		super(sessionId, systemId);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setLoginedInfo(String sessionId, long systemId) {
		((DelGroupReq.Builder)mDataBuilder).setSessionId(sessionId);
		((DelGroupReq.Builder)mDataBuilder).setSystemId(systemId);
		
	}

	@Override
	public void setVersionAndClientType(int version, int clientType) {
		((DelGroupReq.Builder)mDataBuilder).setVersionNo(version);
		((DelGroupReq.Builder)mDataBuilder).setClientType(clientType);
	}
	
	public void setGroupInfo(int groupId)
	{
		((DelGroupReq.Builder)mDataBuilder).setGroupId(groupId);
		super.buildAccessReq(((DelGroupReq.Builder)mDataBuilder).build().toByteString());
	}

	@Override
	public void initmDataBuilder() {
		mDataBuilder = DelGroupReq.newBuilder();
	}
}
