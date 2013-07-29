package com.heme.logic.httpprotocols.groupinfo.updategroup.delmember;

import java.util.List;

import com.heme.logic.httpprotocols.base.BaseLoginedBusinessRequest;
import com.heme.logic.module.Data.DelGroupMemberReq;

public class DelGroupMemberRequest extends BaseLoginedBusinessRequest {
	//删除群好友
	//增加群好友
	DelGroupMemberReq.Builder mDelGroupMemberReqBuilder = DelGroupMemberReq.newBuilder();
	@Override
	public void setLoginedInfo(String sessionId, long systemId) {
		mDelGroupMemberReqBuilder.setSessionId(sessionId);
		mDelGroupMemberReqBuilder.setSystemId(systemId);
		super.setBody(mDelGroupMemberReqBuilder.build().toByteString());
		
	}

	@Override
	public void setVersionAndClientType(int version, int clientType) {
		mDelGroupMemberReqBuilder.setVersionNo(version);
		mDelGroupMemberReqBuilder.setClientType(clientType);
		super.setBody(mDelGroupMemberReqBuilder.build().toByteString());

	}
	
	public void setDelInfo(int groupId,List<Long> memberSystemId)
	{
		mDelGroupMemberReqBuilder.setGroupId(groupId);		
		for (int i = 0; i < memberSystemId.size(); i++) 
		{
			mDelGroupMemberReqBuilder.addMemberSystemId(memberSystemId.get(i));
		}
		super.setBody(mDelGroupMemberReqBuilder.build().toByteString());
	}

	@Override
	public void initmDataBuilder() {
		// TODO Auto-generated method stub
		
	}
}
