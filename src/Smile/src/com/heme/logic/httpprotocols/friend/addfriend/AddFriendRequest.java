package com.heme.logic.httpprotocols.friend.addfriend;

import com.heme.logic.httpprotocols.base.business.BaseLoginedBusinessRequest;
import com.heme.logic.module.Data.FriendVerifyMsgReq;

public class AddFriendRequest extends BaseLoginedBusinessRequest {

	FriendVerifyMsgReq.Builder mFriendVerifyMsgReqBuilder;

	public AddFriendRequest(String sessionId, long systemId) {
		super(sessionId, systemId);
	}

	// 带验证信息或者不带验证信息，都走这个request
	@Override
	public void setLoginedInfo(String sessionId, long systemId) {
		mFriendVerifyMsgReqBuilder.setSessionId(sessionId);
		mFriendVerifyMsgReqBuilder.setSystemId(systemId);
	}

	public void setVerifyInfo(long targetId, String verifyMsg) {
		mFriendVerifyMsgReqBuilder.setTargetSystemId(targetId);
		mFriendVerifyMsgReqBuilder.setVerifyMsg(verifyMsg);

	}

	@Override
	public void setVersionAndClientType(String version, int client_type) {
		mFriendVerifyMsgReqBuilder.setClientType(client_type);
		mFriendVerifyMsgReqBuilder.setVersionNo(version);
		//todo:没有见到这个协议的使用待确定
//		mDataSvrBuilder.setEnumCmd(Cmd.SendFeedback);
//		mDataSvrBuilder.set(mFriendVerifyMsgReqBuilder.build());
		super.setBody(mFriendVerifyMsgReqBuilder.build().toByteString());

	}

	@Override
	public void initmDataBuilder() {
		mFriendVerifyMsgReqBuilder = FriendVerifyMsgReq.newBuilder();
	}
}
