package com.heme.logic.httpprotocols.groupinfo.updategroup;

import com.heme.logic.httpprotocols.base.BaseLoginedBusinessRequest;
import com.heme.logic.module.Data.SetGroupNameReq;
/***
 * 
 * @author rolandxu
 *	操作群信息
 */
public class UpdateGroupRequest extends BaseLoginedBusinessRequest {

	public UpdateGroupRequest(String sessionId, long systemId) {
		super(sessionId, systemId);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setLoginedInfo(String sessionId, long systemId) {
		((SetGroupNameReq.Builder)mDataBuilder).setSessionId(sessionId);
		((SetGroupNameReq.Builder)mDataBuilder).setSystemId(systemId);
		
	}

	@Override
	public void setVersionAndClientType(int version, int clientType) {
		((SetGroupNameReq.Builder)mDataBuilder).setVersionNo(version);
		((SetGroupNameReq.Builder)mDataBuilder).setClientType(clientType);

	}
	
	public void setGroupInfo(String groupName,int groupId)
	{
		((SetGroupNameReq.Builder)mDataBuilder).setGroupName(groupName);
		((SetGroupNameReq.Builder)mDataBuilder).setGroupId(groupId);
		super.buildAccessReq(((SetGroupNameReq.Builder)mDataBuilder).build().toByteString());
	}

	@Override
	public void initmDataBuilder() {
		mDataBuilder = SetGroupNameReq.newBuilder();
	}

}
