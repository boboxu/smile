package com.heme.logic.httpprotocols.friend.updatefriend;

import com.heme.logic.httpprotocols.base.BaseLoginedBusinessRequest;
import com.heme.logic.module.Data.SetFriendDescReq;

public class UpdateFriendRequest extends BaseLoginedBusinessRequest {

	private SetFriendDescReq.Builder mSetFriendDescReqBuilder = SetFriendDescReq.newBuilder();
	@Override
	public void setLoginedInfo(String sessionId, long systemId) {
		mSetFriendDescReqBuilder.setSessionId(sessionId);
		mSetFriendDescReqBuilder.setSystemId(systemId);
		super.setBody(mSetFriendDescReqBuilder.build().toByteString());
		
	}

	@Override
	public void setVersionAndClientType(int version, int client_type) {
		mSetFriendDescReqBuilder.setClientType(client_type);
		mSetFriendDescReqBuilder.setVersionNo(version);
		super.setBody(mSetFriendDescReqBuilder.build().toByteString());
	}

	public void setFriendDescription(long targetId,String dsp)
	{
		mSetFriendDescReqBuilder.setTargetSystemId(targetId);
		mSetFriendDescReqBuilder.setDescription(dsp);
		super.setBody(mSetFriendDescReqBuilder.build().toByteString());
	}
}
