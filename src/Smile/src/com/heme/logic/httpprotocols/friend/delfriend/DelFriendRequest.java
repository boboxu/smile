package com.heme.logic.httpprotocols.friend.delfriend;

import java.util.List;

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

	public void setTargetSystemId(List<Long> targetIdList) {
		mDelFriendReqBuilder.addAllTargetSystemId(targetIdList);

		mDataSvrProtoBuilder.setDelFriendReqInfo(mDelFriendReqBuilder.build());
		mDataSvrProtoBuilder.setEnumCmd(Cmd.DelFriend);
		super.setBody(mDataSvrProtoBuilder.build().toByteString());
	}

	@Override
	public void initmDataBuilder() {
		mDelFriendReqBuilder = DelFriendReq.newBuilder();
	}

}
