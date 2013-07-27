package com.heme.logic.httpprotocols.groupinfo.creategroup.temp;

import java.util.List;

import com.heme.logic.httpprotocols.base.BaseLoginedBusinessRequest;
import com.heme.logic.httpprotocols.groupinfo.creategroup.permanent.CreatePermanentGroupRequest.VERIFYTYPE;
import com.heme.logic.module.Data.CreateTempGroupReq;

public class CreateTempGroupRequest extends BaseLoginedBusinessRequest {

	CreateTempGroupReq.Builder mCreateTempGroupReqBuilder = CreateTempGroupReq.newBuilder();
	@Override
	public void setLoginedInfo(String sessionId, long systemId) {
		mCreateTempGroupReqBuilder.setSessionId(sessionId);
		mCreateTempGroupReqBuilder.setSystemId(systemId);
		super.setBody(mCreateTempGroupReqBuilder.build().toByteString());
		
	}

	@Override
	public void setVersionAndClientType(int version, int clientType) {
		mCreateTempGroupReqBuilder.setVersionNo(version);
		mCreateTempGroupReqBuilder.setClientType(clientType);
		super.setBody(mCreateTempGroupReqBuilder.build().toByteString());

	}
	
	public void setGroupInfo(String groupName,List<Long> memberSystemId)
	{
		mCreateTempGroupReqBuilder.setGroupName(groupName);
		for (int i = 0; i < memberSystemId.size(); i++) 
		{
			mCreateTempGroupReqBuilder.addMemberSystemId(memberSystemId.get(i));
		}
		super.setBody(mCreateTempGroupReqBuilder.build().toByteString());
	}
}
