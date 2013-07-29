package com.heme.logic.httpprotocols.groupinfo.creategroup.temp;

import java.util.List;

import com.heme.logic.httpprotocols.base.BaseLoginedBusinessRequest;
import com.heme.logic.httpprotocols.groupinfo.creategroup.permanent.CreatePermanentGroupRequest.VERIFYTYPE;
import com.heme.logic.module.Data.CreateTempGroupReq;

public class CreateTempGroupRequest extends BaseLoginedBusinessRequest {

	@Override
	public void setLoginedInfo(String sessionId, long systemId) {
		((CreateTempGroupReq.Builder)mDataBuilder).setSessionId(sessionId);
		((CreateTempGroupReq.Builder)mDataBuilder).setSystemId(systemId);
	}

	@Override
	public void setVersionAndClientType(int version, int clientType) {
		((CreateTempGroupReq.Builder)mDataBuilder).setVersionNo(version);
		((CreateTempGroupReq.Builder)mDataBuilder).setClientType(clientType);

	}
	
	public void setGroupInfo(String groupName,List<Long> memberSystemId)
	{
		((CreateTempGroupReq.Builder)mDataBuilder).setGroupName(groupName);
		for (int i = 0; i < memberSystemId.size(); i++) 
		{
			((CreateTempGroupReq.Builder)mDataBuilder).addMemberSystemId(memberSystemId.get(i));
		}
		super.buildAccessReq(((CreateTempGroupReq.Builder)mDataBuilder).build().toByteString());
	}

	@Override
	public void initmDataBuilder() {
		mDataBuilder = CreateTempGroupReq.newBuilder();
	}
}
