package com.heme.logic.httpprotocols.groupinfo.updategroup.addmember;

import java.util.List;

import com.heme.logic.httpprotocols.base.BaseLoginedBusinessRequest;
import com.heme.logic.module.Data.AddGroupMemberReq;

public class AddGroupMemberRequest extends BaseLoginedBusinessRequest {
	public AddGroupMemberRequest(String sessionId, long systemId) {
		super(sessionId, systemId);
		// TODO Auto-generated constructor stub
	}

	//增加群好友
	@Override
	public void setLoginedInfo(String sessionId, long systemId) {
		((AddGroupMemberReq.Builder)mDataBuilder).setSessionId(sessionId);
		((AddGroupMemberReq.Builder)mDataBuilder).setSystemId(systemId);
		
	}

	@Override
	public void setVersionAndClientType(int version, int clientType) {
		((AddGroupMemberReq.Builder)mDataBuilder).setVersionNo(version);
		((AddGroupMemberReq.Builder)mDataBuilder).setClientType(clientType);

	}
	
	public void setAddInfo(int groupId,List<Long> memberSystemId)
	{
		((AddGroupMemberReq.Builder)mDataBuilder).setGroupId(groupId);		
		for (int i = 0; i < memberSystemId.size(); i++) 
		{
			((AddGroupMemberReq.Builder)mDataBuilder).addMemberSystemId(memberSystemId.get(i));
		}
		super.buildAccessReq(((AddGroupMemberReq.Builder)mDataBuilder).build().toByteString());
	}

	@Override
	public void initmDataBuilder() {
		mDataBuilder = AddGroupMemberReq.newBuilder();
	}
}
