package com.heme.logic.httpprotocols.message.sendmsg.base;

import com.google.protobuf.ByteString;
import com.heme.logic.httpprotocols.base.message.messageopr.BaseMessageOprRequest;
import com.heme.logic.module.Message.CommonMsg;
import com.heme.logic.module.Message.SendMsgReq;

public class BaseSendMsgRequest extends BaseMessageOprRequest{

	private SendMsgReq.Builder mSendMsgReqBuilder;
	protected CommonMsg.Builder mCommonMsgBuilder;
	protected BaseSendMsgRequest(long systemId, int sessionId) {
		super(systemId, sessionId);
		mSendMsgReqBuilder = SendMsgReq.newBuilder();
		mCommonMsgBuilder = CommonMsg.newBuilder();
	}

	protected void setCommonMsg(ByteString context) {
		mSendMsgReqBuilder.setMsgSendmsg(mCommonMsgBuilder.build());
		super.setSendMsgReq(mSendMsgReqBuilder.build(),context);
	}
	
}
