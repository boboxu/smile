package com.heme.logic.httpprotocols.friend.updatefriend;

import com.heme.logic.httpprotocols.base.BaseLoginedBusinessRequest;
import com.heme.logic.module.Data.SetFriendDescReq;

public class UpdateFriendRequest extends BaseLoginedBusinessRequest {

	@Override
	public void setLoginedInfo(String sessionId, long systemId) {
		((SetFriendDescReq.Builder)mDataBuilder).setSessionId(sessionId);
		((SetFriendDescReq.Builder)mDataBuilder).setSystemId(systemId);
		
	}

	@Override
	public void setVersionAndClientType(int version, int client_type) {
		((SetFriendDescReq.Builder)mDataBuilder).setClientType(client_type);
		((SetFriendDescReq.Builder)mDataBuilder).setVersionNo(version);
	}

	@Override
	public void initmDataBuilder() {
		mDataBuilder = SetFriendDescReq.newBuilder();
	}
	
	public void setFriendDescription(long targetId,String dsp)
	{
		((SetFriendDescReq.Builder)mDataBuilder).setTargetSystemId(targetId);
		((SetFriendDescReq.Builder)mDataBuilder).setDescription(dsp);
		super.buildAccessReq(((SetFriendDescReq.Builder)mDataBuilder).build().toByteString());
	}
}
