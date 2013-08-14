package com.heme.logic.httpprotocols.friend.addfriend;

import java.util.List;

import com.heme.logic.httpprotocols.base.business.BaseLoginedBusinessRequest;
import com.heme.logic.module.Data.AddFriendReq;
import com.heme.logic.module.Data.DataSvrProto.Cmd;

public class AddFriendRequest extends BaseLoginedBusinessRequest {

	private AddFriendReq.Builder mAddFriendReqBuilder;
	
	public AddFriendRequest(String sessionId, long systemId) {
		super(sessionId, systemId);
	}

	@Override
	protected void setLoginedInfo(String sessionId, long systemId) {
		mAddFriendReqBuilder.setSessionId(sessionId);
		mAddFriendReqBuilder.setSystemId(systemId);
	}

	@Override
	public void setVersionAndClientType(String version, int clientType) {
		mAddFriendReqBuilder.setClientType(clientType);
		mAddFriendReqBuilder.setVersionNo(version);
	}

	@Override
	public void initmDataBuilder() {
		mAddFriendReqBuilder = AddFriendReq.newBuilder();
		
	}

	public void setTargetSystemId(List<Long> targetIdList) {
		mAddFriendReqBuilder.addAllTargetSystemId(targetIdList);

		mDataSvrProtoBuilder.setAddFriendReqInfo(mAddFriendReqBuilder.build());
		mDataSvrProtoBuilder.setEnumCmd(Cmd.AddFriend);
		super.setBody(mDataSvrProtoBuilder.build().toByteString());
	}
}
