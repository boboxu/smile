package com.heme.logic.httpprotocols.friend.delfriend;

import com.heme.logic.httpprotocols.base.BaseLoginedBusinessRequest;
import com.heme.logic.module.Data.DelFriendReq;

public class DelFriendRequest extends BaseLoginedBusinessRequest {
	private DelFriendReq.Builder mDelFriendReqBuilder = DelFriendReq.newBuilder();
	@Override
	public void setLoginedInfo(String sessionId, long systemId) {
		mDelFriendReqBuilder.setSessionId(sessionId);
		mDelFriendReqBuilder.setSystemId(systemId);
		super.setBody(mDelFriendReqBuilder.build().toByteString());
		
	}

	@Override
	public void setVersionAndClientType(int version, int client_type) {
		mDelFriendReqBuilder.setClientType(client_type);
		mDelFriendReqBuilder.setVersionNo(version);
		super.setBody(mDelFriendReqBuilder.build().toByteString());
	}

	public void setTargetSystemId(long targetId)
	{
		mDelFriendReqBuilder.setTargetSystemId(targetId);
		super.setBody(mDelFriendReqBuilder.build().toByteString());
	}
	
}
