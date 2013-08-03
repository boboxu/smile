package com.heme.logic.httpprotocols.message;

import com.heme.logic.httpprotocols.base.message.messageopr.BaseMessageOprRequest;
import com.heme.logic.module.Message.PollUnreadInfoReq;

public class PollUnreadMessageRequest extends BaseMessageOprRequest {
	PollUnreadInfoReq.Builder mPollUnreadInfoReqBuilder;
	protected PollUnreadMessageRequest(long systemId, int sessionId) {
		super(systemId, sessionId);
		mPollUnreadInfoReqBuilder = PollUnreadInfoReq.newBuilder();
		mPollUnreadInfoReqBuilder.setUint64Uid(systemId);
	}
	
}
