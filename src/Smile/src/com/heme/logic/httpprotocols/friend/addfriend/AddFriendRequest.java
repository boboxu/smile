package com.heme.logic.httpprotocols.friend.addfriend;

import com.heme.logic.httpprotocols.base.BaseLoginedBusinessRequest;
import com.heme.logic.module.Data.FriendVerifyMsgReq;

public class AddFriendRequest extends BaseLoginedBusinessRequest {
	// 带验证信息或者不带验证信息，都走这个request
	@Override
	public void setLoginedInfo(String sessionId, long systemId) {
		((FriendVerifyMsgReq.Builder) mDataBuilder).setSessionId(sessionId);
		((FriendVerifyMsgReq.Builder) mDataBuilder).setSystemId(systemId);
	}

	public void setVerifyInfo(long targetId, String verifyMsg) {
		((FriendVerifyMsgReq.Builder) mDataBuilder).setTargetSystemId(targetId);
		((FriendVerifyMsgReq.Builder) mDataBuilder).setVerifyMsg(verifyMsg);

	}

	@Override
	public void setVersionAndClientType(int version, int client_type) {
		((FriendVerifyMsgReq.Builder) mDataBuilder).setClientType(client_type);
		((FriendVerifyMsgReq.Builder) mDataBuilder).setVersionNo(version);
		super.buildAccessReq(((FriendVerifyMsgReq.Builder) mDataBuilder)
				.build().toByteString());

	}

	@Override
	public void initmDataBuilder() {
		mDataBuilder = FriendVerifyMsgReq.newBuilder();
	}
}
