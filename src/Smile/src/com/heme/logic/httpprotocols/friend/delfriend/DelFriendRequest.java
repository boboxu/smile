package com.heme.logic.httpprotocols.friend.delfriend;

import com.heme.logic.httpprotocols.base.business.BaseLoginedBusinessRequest;
import com.heme.logic.module.Data.DataSvrProto.Cmd;
import com.heme.logic.module.Data.DelFriendReq;

public class DelFriendRequest extends BaseLoginedBusinessRequest {

	private DelFriendReq.Builder mDelFriendReqBuilder;

	public DelFriendRequest(String sessionId, long systemId) {
		super(sessionId, systemId);
	}

	@Override
	public void setLoginedInfo(String sessionId, long systemId) {
		mDelFriendReqBuilder.setSessionId(sessionId);
		mDelFriendReqBuilder.setSystemId(systemId);

	}

	@Override
	public void setVersionAndClientType(String version, int client_type) {
		mDelFriendReqBuilder.setClientType(client_type);
		mDelFriendReqBuilder.setVersionNo(version);
	}

	public void setTargetSystemId(long targetId) {
		mDelFriendReqBuilder.setTargetSystemId(targetId);

		mDataSvrProtoBuilder.setDelFriendReqInfo(mDelFriendReqBuilder.build());
		mDataSvrProtoBuilder.setEnumCmd(Cmd.DelFriend);
		super.setBody(mDataSvrProtoBuilder.build().toByteString());
	}

	@Override
	public void initmDataBuilder() {
		mDelFriendReqBuilder = DelFriendReq.newBuilder();
	}

}
