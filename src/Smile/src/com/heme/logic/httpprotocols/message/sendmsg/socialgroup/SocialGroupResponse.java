package com.heme.logic.httpprotocols.message.sendmsg.socialgroup;

import com.google.protobuf.InvalidProtocolBufferException;
import com.heme.logic.httpprotocols.message.sendmsg.base.BaseMessageResponse;

public class SocialGroupResponse extends BaseMessageResponse {

	//直接回包就是SendMsg的Res，成功或者失败
	@Override
	public void parseData() throws InvalidProtocolBufferException {
		super.parseData();
	}
	
}
