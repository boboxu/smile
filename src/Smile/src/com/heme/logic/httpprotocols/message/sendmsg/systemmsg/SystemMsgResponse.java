package com.heme.logic.httpprotocols.message.sendmsg.systemmsg;

import com.google.protobuf.InvalidProtocolBufferException;
import com.heme.logic.httpprotocols.message.sendmsg.base.BaseMessageResponse;

public class SystemMsgResponse extends BaseMessageResponse {

	//直接回包就是SendMsg的Res，成功或者失败
	@Override
	public void parseData() throws InvalidProtocolBufferException {
		super.parseData();
	}
	
}
