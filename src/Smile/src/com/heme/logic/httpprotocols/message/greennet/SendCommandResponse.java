package com.heme.logic.httpprotocols.message.greennet;

import com.google.protobuf.InvalidProtocolBufferException;
import com.heme.logic.httpprotocols.base.message.BaseMessageResponse;

public class SendCommandResponse extends BaseMessageResponse {

	//直接回包就是SendMsg的Res，成功或者失败
	@Override
	public void parseData() throws InvalidProtocolBufferException {
		super.parseData();
	}
	
}
