package com.heme.logic.httpprotocols.groupinfo.updategroup;

import com.heme.logic.httpprotocols.base.business.BaseLoginedBusinessRequest;
import com.heme.logic.module.Data.SetGroupNameReq;
import com.heme.logic.module.Data.DataSvrProto.Cmd;
/***
 * 
 * @author rolandxu
 *	操作群信息
 */
public class UpdateGroupRequest extends BaseLoginedBusinessRequest {

	SetGroupNameReq.Builder mSetGroupNameReqBuilder;
	public UpdateGroupRequest(String sessionId, long systemId) {
		super(sessionId, systemId);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setLoginedInfo(String sessionId, long systemId) {
		mSetGroupNameReqBuilder.setSessionId(sessionId);
		mSetGroupNameReqBuilder.setSystemId(systemId);
		
	}

	@Override
	public void setVersionAndClientType(String version, int clientType) {
		mSetGroupNameReqBuilder.setVersionNo(version);
		mSetGroupNameReqBuilder.setClientType(clientType);

	}
	
	public void setGroupInfo(String groupName,int groupId)
	{
		mSetGroupNameReqBuilder.setGroupName(groupName);
		mSetGroupNameReqBuilder.setGroupId(groupId);
		mDataSvrProtoBuilder.setSetGroupNameReqInfo(mSetGroupNameReqBuilder.build());
		mDataSvrProtoBuilder.setEnumCmd(Cmd.SetGroupName);
		super.setBody(mSetGroupNameReqBuilder.build().toByteString());
	}

	@Override
	public void initmDataBuilder() {
		mSetGroupNameReqBuilder = SetGroupNameReq.newBuilder();
	}

}
