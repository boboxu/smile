package com.heme.logic.httpprotocols.groupinfo.deletegroup;

import java.util.List;

import com.heme.logic.httpprotocols.base.BaseLoginedBusinessRequest;
import com.heme.logic.module.Data.DelGroupReq;

public class DeleteGroupRequest extends BaseLoginedBusinessRequest {
	//删除群

	DelGroupReq.Builder mDelGroupReqBuilder = DelGroupReq.newBuilder();
	@Override
	public void setLoginedInfo(String sessionId, long systemId) {
		mDelGroupReqBuilder.setSessionId(sessionId);
		mDelGroupReqBuilder.setSystemId(systemId);
		super.setBody(mDelGroupReqBuilder.build().toByteString());
		
	}

	@Override
	public void setVersionAndClientType(int version, int clientType) {
		mDelGroupReqBuilder.setVersionNo(version);
		mDelGroupReqBuilder.setClientType(clientType);
		super.setBody(mDelGroupReqBuilder.build().toByteString());

	}
	
	public void setGroupInfo(int groupId)
	{
		mDelGroupReqBuilder.setGroupId(groupId);
		super.setBody(mDelGroupReqBuilder.build().toByteString());
	}
}
