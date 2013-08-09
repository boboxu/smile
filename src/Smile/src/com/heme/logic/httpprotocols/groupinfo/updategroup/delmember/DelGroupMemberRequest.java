package com.heme.logic.httpprotocols.groupinfo.updategroup.delmember;

import java.util.List;

import com.heme.logic.httpprotocols.base.business.BaseLoginedBusinessRequest;
import com.heme.logic.module.Data.DelGroupMemberReq;
import com.heme.logic.module.Data.DataSvrProto.Cmd;

public class DelGroupMemberRequest extends BaseLoginedBusinessRequest {
	
	DelGroupMemberReq.Builder mDelGroupMemberReqBuilder;
	public DelGroupMemberRequest(String sessionId, long systemId) {
		super(sessionId, systemId);
		// TODO Auto-generated constructor stub
	}

	//删除群好友
	@Override
	public void setLoginedInfo(String sessionId, long systemId) {
		mDelGroupMemberReqBuilder.setSessionId(sessionId);
		mDelGroupMemberReqBuilder.setSystemId(systemId);
		
	}

	@Override
	public void setVersionAndClientType(String version, int clientType) {
		mDelGroupMemberReqBuilder.setVersionNo(version);
		mDelGroupMemberReqBuilder.setClientType(clientType);

	}
	
	public void setDelInfo(int groupId,List<Long> memberSystemId)
	{
		mDelGroupMemberReqBuilder.setGroupId(groupId);		
		mDelGroupMemberReqBuilder.addAllMemberSystemId(memberSystemId);
		mDataSvrProtoBuilder.setEnumCmd(Cmd.DelGroupMember);
		mDataSvrProtoBuilder.setDelGroupMemberReqInfo(mDelGroupMemberReqBuilder.build());
		super.setBody(mDataSvrProtoBuilder.build().toByteString());
	}

	@Override
	public void initmDataBuilder() {
		mDelGroupMemberReqBuilder = DelGroupMemberReq.newBuilder();
	}
}
