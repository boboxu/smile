package com.heme.logic.httpprotocols.base.message.messageopr;

import com.google.protobuf.InvalidProtocolBufferException;
import com.heme.logic.httpprotocols.base.BasePbResponse;
import com.heme.logic.module.Message.MessageOpr;
import com.heme.logic.module.Message.PollMsgRes;
import com.heme.logic.module.Message.PollUnreadInfoRes;
import com.heme.logic.module.Message.PushMsgRes;

public class BaseMessageOprResponse extends BasePbResponse{
	
	protected MessageOpr mMessageOpr;
	
	@Override
	public void parseData() throws InvalidProtocolBufferException {
		// TODO Auto-generated method stub
		super.parseData();
		mMessageOpr = MessageOpr.parseFrom(mAccessRespData
				.getBytesBody());
	}
	
	public PushMsgRes getPushMsgRes() throws InvalidProtocolBufferException
	{
		return PushMsgRes.parseFrom(mAccessRespData
				.getBytesBody());
	}
	
	public PollMsgRes getPollMsgRes() throws InvalidProtocolBufferException
	{
		return PollMsgRes.parseFrom(mAccessRespData
				.getBytesBody());
	}
	
	public PollUnreadInfoRes getPollUnreadMsgRes() throws InvalidProtocolBufferException
	{
		return PollUnreadInfoRes.parseFrom(mAccessRespData
				.getBytesBody());
	}
}
