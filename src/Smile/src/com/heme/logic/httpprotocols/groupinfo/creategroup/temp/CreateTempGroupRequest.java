package com.heme.logic.httpprotocols.groupinfo.creategroup.temp;

import java.util.List;

import com.heme.logic.httpprotocols.base.business.BaseLoginedBusinessRequest;
import com.heme.logic.module.Data.CreateTempGroupReq;
import com.heme.logic.module.Data.DataSvrProto.Cmd;

public class CreateTempGroupRequest extends BaseLoginedBusinessRequest {

	CreateTempGroupReq.Builder mCreateTempGroupBuilder;
	
	public CreateTempGroupRequest(String sessionId, long systemId) {
		super(sessionId, systemId);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setLoginedInfo(String sessionId, long systemId) {
		mCreateTempGroupBuilder.setSessionId(sessionId);
		mCreateTempGroupBuilder.setSystemId(systemId);
	}

	@Override
	public void setVersionAndClientType(String version, int clientType) {
		mCreateTempGroupBuilder.setVersionNo(version);
		mCreateTempGroupBuilder.setClientType(clientType);

	}
	
	public void setGroupInfo(String groupName,List<Long> memberSystemId)
	{
		mCreateTempGroupBuilder.setGroupName(groupName);
		mCreateTempGroupBuilder.addAllMemberSystemId(memberSystemId);
		mDataSvrProtoBuilder.setEnumCmd(Cmd.CreateTempGroup);
		mDataSvrProtoBuilder.setCreateTempGroupReqInfo(mCreateTempGroupBuilder.build());
		super.setBody(mDataSvrProtoBuilder.build().toByteString());
	}

	@Override
	public void initmDataBuilder() {
		mCreateTempGroupBuilder = CreateTempGroupReq.newBuilder();
	}
}
