package com.heme.logic.httpprotocols.message.pullunreadmsg;

import com.google.protobuf.InvalidProtocolBufferException;
import com.heme.logic.httpprotocols.base.message.BaseMessageOprResponse;
import com.heme.logic.module.Message.PollUnreadInfoRes;

public class PollUnreadMessageResponse extends BaseMessageOprResponse {

	@Override
	public void parseData() throws InvalidProtocolBufferException {
		// TODO Auto-generated method stub
		super.parseData();
	}

	public PollUnreadInfoRes getUnreadInfoRes()
	{
		return mMessageOpr.getMsgPollUnreadRes();
	}
	
}
