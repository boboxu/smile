package com.heme.logic.httpprotocols.groupinfo.getgroup;

import java.util.List;

import com.heme.logic.httpprotocols.base.business.BaseLoginedBusinessRequest;
import com.heme.logic.module.Data.DataSvrProto.Cmd;
import com.heme.logic.module.Data.GetGroupInfoReq;

public class GetGroupInfoRequest extends BaseLoginedBusinessRequest {
	
	GetGroupInfoReq.Builder mGetGroupInfoReqBuilder;
	public GetGroupInfoRequest(String sessionId, long systemId) {
		super(sessionId, systemId);
		// TODO Auto-generated constructor stub
	}

	//获取群信息
	@Override
	public void setLoginedInfo(String sessionId, long systemId) {
		mGetGroupInfoReqBuilder.setSessionId(sessionId);
		mGetGroupInfoReqBuilder.setSystemId(systemId);
	}

	@Override
	public void setVersionAndClientType(String version, int clientType) {
		mGetGroupInfoReqBuilder.setVersionNo(version);
		mGetGroupInfoReqBuilder.setClientType(clientType);
	}
	
	public void setGroupId(List<Integer> groupIdList)
	{
		mGetGroupInfoReqBuilder.addAllGroupId(groupIdList);
		mDataSvrProtoBuilder.setEnumCmd(Cmd.GetGroupInfo);
		mDataSvrProtoBuilder.setGetGroupInfoReqInfo(mGetGroupInfoReqBuilder.build());
		super.setBody(mDataSvrProtoBuilder.build().toByteString());
	}

	@Override
	public void initmDataBuilder() {
		mGetGroupInfoReqBuilder = GetGroupInfoReq.newBuilder();
	}
}
