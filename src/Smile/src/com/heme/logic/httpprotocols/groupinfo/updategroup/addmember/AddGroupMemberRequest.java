package com.heme.logic.httpprotocols.groupinfo.updategroup.addmember;

import java.util.List;

import com.heme.logic.httpprotocols.base.business.BaseLoginedBusinessRequest;
import com.heme.logic.module.Data.AddGroupMemberReq;
import com.heme.logic.module.Data.DataSvrProto.Cmd;

public class AddGroupMemberRequest extends BaseLoginedBusinessRequest {
	
	AddGroupMemberReq.Builder mAddGroupMemberReqBuilder;
	
	public AddGroupMemberRequest(String sessionId, long systemId) {
		super(sessionId, systemId);
	}

	//增加群好友
	@Override
	public void setLoginedInfo(String sessionId, long systemId) {
		mAddGroupMemberReqBuilder.setSessionId(sessionId);
		mAddGroupMemberReqBuilder.setSystemId(systemId);
		
	}

	@Override
	public void setVersionAndClientType(String version, int clientType) {
		mAddGroupMemberReqBuilder.setVersionNo(version);
		mAddGroupMemberReqBuilder.setClientType(clientType);

	}
	
	public void setAddInfo(int groupId,List<Long> memberSystemId)
	{
		mAddGroupMemberReqBuilder.setGroupId(groupId);		
		mAddGroupMemberReqBuilder.addAllMemberSystemId(memberSystemId);
		mDataSvrProtoBuilder.setEnumCmd(Cmd.AddGroupMember);
		mDataSvrProtoBuilder.setAddGroupMemberReqInfo(mAddGroupMemberReqBuilder.build());
		super.setBody(mDataSvrProtoBuilder.build().toByteString());
	}

	@Override
	public void initmDataBuilder() {
		mAddGroupMemberReqBuilder = AddGroupMemberReq.newBuilder();
	}
}
