package com.heme.logic.httpprotocols.message.pullunreadmsg;

import com.google.protobuf.InvalidProtocolBufferException;
import com.heme.logic.httpprotocols.base.message.messageopr.BaseMessageOprResponse;
import com.heme.logic.module.Message.PollUnreadInfoRes;

public class PollUnreadMessageResponse extends BaseMessageOprResponse {
	private PollUnreadInfoRes mPollUnreadInfoRes;
	
	@Override
	public void parseData() throws InvalidProtocolBufferException {
		// TODO Auto-generated method stub
		super.parseData();
		mPollUnreadInfoRes = mMessageOpr.getMsgPollUnreadRes();
	}
	
	public PollUnreadInfoRes getUnreadInfoRes()
	{
		return mPollUnreadInfoRes;
	}
	
}
