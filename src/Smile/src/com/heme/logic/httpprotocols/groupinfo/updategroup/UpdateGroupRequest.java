package com.heme.logic.httpprotocols.groupinfo.updategroup;

import java.util.List;

import com.heme.logic.httpprotocols.base.BaseLoginedBusinessRequest;
import com.heme.logic.module.Data.SetGroupNameReq;
/***
 * 
 * @author rolandxu
 *	操作群信息
 */
public class UpdateGroupRequest extends BaseLoginedBusinessRequest {

	SetGroupNameReq.Builder mSetGroupNameReqBuilder = SetGroupNameReq.newBuilder();
	@Override
	public void setLoginedInfo(String sessionId, long systemId) {
		mSetGroupNameReqBuilder.setSessionId(sessionId);
		mSetGroupNameReqBuilder.setSystemId(systemId);
		super.setBody(mSetGroupNameReqBuilder.build().toByteString());
		
	}

	@Override
	public void setVersionAndClientType(int version, int clientType) {
		mSetGroupNameReqBuilder.setVersionNo(version);
		mSetGroupNameReqBuilder.setClientType(clientType);
		super.setBody(mSetGroupNameReqBuilder.build().toByteString());

	}
	
	public void setGroupInfo(String groupName,int groupId)
	{
		mSetGroupNameReqBuilder.setGroupName(groupName);
		mSetGroupNameReqBuilder.setGroupId(groupId);
		super.setBody(mSetGroupNameReqBuilder.build().toByteString());
	}

}
