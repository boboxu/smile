package com.heme.logic.httpprotocols.message.pollmsg;

import com.google.protobuf.ByteString;
import com.heme.logic.httpprotocols.base.message.messageopr.BaseMessageOprRequest;
import com.heme.logic.module.Message.PollMsgReq;

public class PollMessageRequest extends BaseMessageOprRequest {
	
	PollMsgReq.Builder mPollMsgbBuilder;
	protected PollMessageRequest(long systemId, String sessionId) {
		super(systemId, sessionId);
		mPollMsgbBuilder = PollMsgReq.newBuilder();
		mPollMsgbBuilder.setUint64Uid(systemId);
	}

	public void setPollMsgInfo(MSGTYPE msgtype,long fromId,int time,ByteString context)
	{
		mPollMsgbBuilder.setUint32MsgType(MSGTYPE.value(msgtype));
		mPollMsgbBuilder.setUint64FromUid(fromId);
		mPollMsgbBuilder.setUint64Time(System.currentTimeMillis());
		super.setPollMsgReq(mPollMsgbBuilder.build(), context);
	}
}
