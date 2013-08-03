package com.heme.logic.httpprotocols.base.message;

import java.util.List;

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
		mCommonMsgBuilder.setUint64FromUid(systemId);
	}

	protected void setCommonMsg(CommonMsg msg,ByteString context) {
		mSendMsgReqBuilder.setMsgSendmsg(msg);
		super.setSendMsgReq(mSendMsgReqBuilder.build(),context);
	}
	
//	public void setCommonInfo(List<Long> targetUidList,List<Long> targetGidList,)
}
