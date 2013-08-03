package com.heme.logic.httpprotocols.message.sendmsg.base;

import com.google.protobuf.InvalidProtocolBufferException;
import com.heme.logic.httpprotocols.base.message.messageopr.BaseMessageOprResponse;
import com.heme.logic.module.Message.SendMsgRes;

public class BaseSendMsgResponse extends BaseMessageOprResponse {
	protected SendMsgRes mSendMsgRes;
	
	@Override
	public void parseData() throws InvalidProtocolBufferException {
		// TODO Auto-generated method stub
		super.parseData();
		mSendMsgRes = mMessageOpr.getMsgSendRes();
	}
	
	public SendMsgRes getSendMsgRes() 
	{
		return mSendMsgRes;
	}
}
