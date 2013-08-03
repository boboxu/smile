package com.heme.logic.httpprotocols.friend.delfriend;

import com.heme.logic.httpprotocols.base.BaseLoginedBusinessRequest;
import com.heme.logic.module.Data.DelFriendReq;

public class DelFriendRequest extends BaseLoginedBusinessRequest {
	public DelFriendRequest(String sessionId, long systemId) {
		super(sessionId, systemId);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setLoginedInfo(String sessionId, long systemId) {
		((DelFriendReq.Builder) mDataBuilder).setSessionId(sessionId);
		((DelFriendReq.Builder) mDataBuilder).setSystemId(systemId);

	}

	@Override
	public void setVersionAndClientType(String version, int client_type) {
		((DelFriendReq.Builder) mDataBuilder).setClientType(client_type);
		((DelFriendReq.Builder) mDataBuilder).setVersionNo(version);
	}

	public void setTargetSystemId(long targetId) {
		((DelFriendReq.Builder) mDataBuilder).setTargetSystemId(targetId);
		super.buildAccessReq(((DelFriendReq.Builder) mDataBuilder).build()
				.toByteString());
	}

	@Override
	public void initmDataBuilder() {
		mDataBuilder = DelFriendReq.newBuilder();
	}

}
