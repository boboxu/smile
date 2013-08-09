package com.heme.logic.httpprotocols.groupinfo.deletegroup;

import com.heme.logic.httpprotocols.base.business.BaseLoginedBusinessRequest;
import com.heme.logic.module.Data.DataSvrProto.Cmd;
import com.heme.logic.module.Data.DelGroupMemberReq;
import com.heme.logic.module.Data.DelGroupReq;

public class DeleteGroupRequest extends BaseLoginedBusinessRequest {
	//删除群

	DelGroupReq.Builder mDelGroupReqBuilder;
	public DeleteGroupRequest(String sessionId, long systemId) {
		super(sessionId, systemId);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setLoginedInfo(String sessionId, long systemId) {
		mDelGroupReqBuilder.setSessionId(sessionId);
		mDelGroupReqBuilder.setSystemId(systemId);
		
	}

	@Override
	public void setVersionAndClientType(String version, int clientType) {
		mDelGroupReqBuilder.setVersionNo(version);
		mDelGroupReqBuilder.setClientType(clientType);
	}
	
	public void setGroupInfo(int groupId)
	{
		mDelGroupReqBuilder.setGroupId(groupId);
		mDataSvrProtoBuilder.setDelGroupReqInfo(mDelGroupReqBuilder.build());
		mDataSvrProtoBuilder.setEnumCmd(Cmd.DelGroup);
		super.setBody(mDataSvrProtoBuilder.build().toByteString());
	}

	@Override
	public void initmDataBuilder() {
		mDelGroupReqBuilder = DelGroupReq.newBuilder();
	}
}
