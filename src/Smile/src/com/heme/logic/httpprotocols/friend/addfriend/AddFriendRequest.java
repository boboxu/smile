package com.heme.logic.httpprotocols.friend.addfriend;

import com.heme.logic.httpprotocols.base.BaseLoginedBusinessRequest;
import com.heme.logic.module.Data.FriendVerifyMsgReq;

public class AddFriendRequest extends BaseLoginedBusinessRequest {

	private FriendVerifyMsgReq.Builder mFriendVerifyDataBuilder = FriendVerifyMsgReq.newBuilder();
	//带验证信息或者不带验证信息，都走这个request
	
	@Override
	public void setLoginedInfo(String sessionId, long systemId) {
		mFriendVerifyDataBuilder.setSessionId(sessionId);
		mFriendVerifyDataBuilder.setSystemId(systemId);
		super.setBody(mFriendVerifyDataBuilder.build().toByteString());
	}
	
	public void setVerifyInfo(long targetId,String verifyMsg)
	{
		mFriendVerifyDataBuilder.setTargetSystemId(targetId);
		mFriendVerifyDataBuilder.setVerifyMsg(verifyMsg);
		super.setBody(mFriendVerifyDataBuilder.build().toByteString());
		
	}
	@Override
	public void setVersionAndClientType(int version, int client_type) {
		mFriendVerifyDataBuilder.setClientType(client_type);
		mFriendVerifyDataBuilder.setVersionNo(version);
		super.setBody(mFriendVerifyDataBuilder.build().toByteString());
		
	}

	@Override
	public void initmDataBuilder() {
		// TODO Auto-generated method stub
		
	}
}
