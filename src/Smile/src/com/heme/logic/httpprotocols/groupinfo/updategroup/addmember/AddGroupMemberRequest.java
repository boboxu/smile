package com.heme.logic.httpprotocols.groupinfo.updategroup.addmember;

import java.util.List;

import com.heme.logic.httpprotocols.base.BaseLoginedBusinessRequest;
import com.heme.logic.module.Data.AddGroupMemberReq;

public class AddGroupMemberRequest extends BaseLoginedBusinessRequest {
	//增加群好友
	AddGroupMemberReq.Builder mAddGroupMemberReqBuilder = AddGroupMemberReq.newBuilder();
	@Override
	public void setLoginedInfo(String sessionId, long systemId) {
		mAddGroupMemberReqBuilder.setSessionId(sessionId);
		mAddGroupMemberReqBuilder.setSystemId(systemId);
		super.setBody(mAddGroupMemberReqBuilder.build().toByteString());
		
	}

	@Override
	public void setVersionAndClientType(int version, int clientType) {
		mAddGroupMemberReqBuilder.setVersionNo(version);
		mAddGroupMemberReqBuilder.setClientType(clientType);
		super.setBody(mAddGroupMemberReqBuilder.build().toByteString());

	}
	
	public void setAddInfo(int groupId,List<Long> memberSystemId)
	{
		mAddGroupMemberReqBuilder.setGroupId(groupId);		
		for (int i = 0; i < memberSystemId.size(); i++) 
		{
			mAddGroupMemberReqBuilder.addMemberSystemId(memberSystemId.get(i));
		}
		super.setBody(mAddGroupMemberReqBuilder.build().toByteString());
	}
}
