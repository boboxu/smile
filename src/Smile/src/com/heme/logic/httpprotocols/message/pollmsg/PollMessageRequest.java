package com.heme.logic.httpprotocols.message.pollmsg;

import com.google.protobuf.ByteString;
import com.heme.logic.httpprotocols.base.message.BaseMessageOprRequest;
import com.heme.logic.module.Message.PollMsgReq;

public class PollMessageRequest extends BaseMessageOprRequest {
	
	PollMsgReq.Builder mPollMsgbBuilder;

	//一定要设置type和默认的toid
	public PollMessageRequest(long systemId, String sessionId,MSGTYPE type) {
		super(systemId, sessionId);
		mPollMsgbBuilder = PollMsgReq.newBuilder();
		mPollMsgbBuilder.setUint64Uid(systemId);
		mPollMsgbBuilder.setUint32MsgType(MSGTYPE.value(type));
		super.setPollMsgReq(mPollMsgbBuilder.build(), ByteString.EMPTY);
	}
	
	public void setPollMsgFromUid(long uid,ByteString context)
	{
		mPollMsgbBuilder.setUint64FromUid(uid);
		super.setPollMsgReq(mPollMsgbBuilder.build(), context);
	}
	
	public void setPollMsgFromGid(long gid,ByteString context)
	{
		mPollMsgbBuilder.setUint64Gid(gid);
		super.setPollMsgReq(mPollMsgbBuilder.build(), context);
	}
}
