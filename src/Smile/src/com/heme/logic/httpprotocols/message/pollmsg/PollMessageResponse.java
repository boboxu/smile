package com.heme.logic.httpprotocols.message.pollmsg;

import com.google.protobuf.InvalidProtocolBufferException;
import com.heme.logic.httpprotocols.base.message.BaseMessageOprResponse;
import com.heme.logic.module.Message.PollMsgRes;

public class PollMessageResponse extends BaseMessageOprResponse {
	
	@Override
	public void parseData() throws InvalidProtocolBufferException {
		// TODO Auto-generated method stub
		super.parseData();
	}
	
	public PollMsgRes getPollMsgRes()
	{
		return mMessageOpr.getMsgPollRes();
	}
}
