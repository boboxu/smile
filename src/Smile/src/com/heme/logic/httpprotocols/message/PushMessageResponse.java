package com.heme.logic.httpprotocols.message;

import com.google.protobuf.InvalidProtocolBufferException;
import com.heme.logic.httpprotocols.base.message.messageopr.BaseMessageOprResponse;
import com.heme.logic.module.Message.PushMsgRes;

public class PushMessageResponse extends BaseMessageOprResponse {
	private PushMsgRes mPushMsgRes;
	@Override
	public void parseData() throws InvalidProtocolBufferException {
		super.parseData();
		mPushMsgRes = mMessageOpr.getMsgPushRes();
	}
	
	public PushMsgRes getPushMsgRes()
	{
		return mPushMsgRes;
	}
}
