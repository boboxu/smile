package com.heme.logic.httpprotocols.groupinfo.updategroup.delmember;

import java.util.List;

import com.heme.logic.httpprotocols.base.BaseLoginedBusinessRequest;
import com.heme.logic.module.Data.DelGroupMemberReq;

public class DelGroupMemberRequest extends BaseLoginedBusinessRequest {
	public DelGroupMemberRequest(String sessionId, long systemId) {
		super(sessionId, systemId);
		// TODO Auto-generated constructor stub
	}

	//删除群好友
	@Override
	public void setLoginedInfo(String sessionId, long systemId) {
		((DelGroupMemberReq.Builder)mDataBuilder).setSessionId(sessionId);
		((DelGroupMemberReq.Builder)mDataBuilder).setSystemId(systemId);
		
	}

	@Override
	public void setVersionAndClientType(String version, int clientType) {
		((DelGroupMemberReq.Builder)mDataBuilder).setVersionNo(version);
		((DelGroupMemberReq.Builder)mDataBuilder).setClientType(clientType);

	}
	
	public void setDelInfo(int groupId,List<Long> memberSystemId)
	{
		((DelGroupMemberReq.Builder)mDataBuilder).setGroupId(groupId);		
		for (int i = 0; i < memberSystemId.size(); i++) 
		{
			((DelGroupMemberReq.Builder)mDataBuilder).addMemberSystemId(memberSystemId.get(i));
		}
		super.setBody(((DelGroupMemberReq.Builder)mDataBuilder).build().toByteString());
	}

	@Override
	public void initmDataBuilder() {
		mDataBuilder = DelGroupMemberReq.newBuilder();
	}
}
