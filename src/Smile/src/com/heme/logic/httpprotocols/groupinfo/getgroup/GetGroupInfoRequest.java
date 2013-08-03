package com.heme.logic.httpprotocols.groupinfo.getgroup;

import java.util.List;

import com.heme.logic.httpprotocols.base.BaseLoginedBusinessRequest;
import com.heme.logic.module.Data.GetGroupInfoReq;

public class GetGroupInfoRequest extends BaseLoginedBusinessRequest {
	public GetGroupInfoRequest(String sessionId, long systemId) {
		super(sessionId, systemId);
		// TODO Auto-generated constructor stub
	}

	//获取群信息
	@Override
	public void setLoginedInfo(String sessionId, long systemId) {
		((GetGroupInfoReq.Builder)mDataBuilder).setSessionId(sessionId);
		((GetGroupInfoReq.Builder)mDataBuilder).setSystemId(systemId);
	}

	@Override
	public void setVersionAndClientType(String version, int clientType) {
		((GetGroupInfoReq.Builder)mDataBuilder).setVersionNo(version);
		((GetGroupInfoReq.Builder)mDataBuilder).setClientType(clientType);
	}
	
	public void setGroupId(List<Integer> groupIdList)
	{
		for (int i = 0; i < groupIdList.size(); i++) {
			((GetGroupInfoReq.Builder)mDataBuilder).addGroupId(groupIdList.get(i));
		}
		super.buildAccessReq(((GetGroupInfoReq.Builder)mDataBuilder).build().toByteString());
	}

	@Override
	public void initmDataBuilder() {
		mDataBuilder = GetGroupInfoReq.newBuilder();
	}
}
