package com.heme.logic.httpprotocols.friend.updatefriend;

import com.heme.logic.httpprotocols.base.business.BaseLoginedBusinessRequest;
import com.heme.logic.module.Data.SetFriendDescReq;
import com.heme.logic.module.Data.DataSvrProto.Cmd;

public class UpdateFriendRequest extends BaseLoginedBusinessRequest {

	private SetFriendDescReq.Builder mSetFriendDescReqBuilder;
	public UpdateFriendRequest(String sessionId, long systemId) {
		super(sessionId, systemId);
	}

	@Override
	public void setLoginedInfo(String sessionId, long systemId) {
		mSetFriendDescReqBuilder.setSessionId(sessionId);
		mSetFriendDescReqBuilder.setSystemId(systemId);
	}

	@Override
	public void setVersionAndClientType(String version, int client_type) {
		mSetFriendDescReqBuilder.setClientType(client_type);
		mSetFriendDescReqBuilder.setVersionNo(version);
	}

	@Override
	public void initmDataBuilder() {
		mSetFriendDescReqBuilder = SetFriendDescReq.newBuilder();
	}
	
	public void setFriendDescription(long targetId,String dsp)
	{
		mSetFriendDescReqBuilder.setTargetSystemId(targetId);
		mSetFriendDescReqBuilder.setDescription(dsp);
		
		mDataSvrProtoBuilder.setEnumCmd(Cmd.SetFriendDesc);
		mDataSvrProtoBuilder.setSetFriendDescReqInfo(mSetFriendDescReqBuilder.build());
		super.setBody(mDataSvrProtoBuilder.build().toByteString());
	}
}
