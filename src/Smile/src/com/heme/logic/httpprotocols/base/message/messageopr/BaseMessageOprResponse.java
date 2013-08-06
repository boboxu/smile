package com.heme.logic.httpprotocols.base.message.messageopr;

import com.google.protobuf.InvalidProtocolBufferException;
import com.heme.logic.httpprotocols.base.BasePbResponse;
import com.heme.logic.module.Message.MessageOpr;
import com.heme.logic.module.Message.PollMsgRes;
import com.heme.logic.module.Message.PollUnreadInfoRes;

public class BaseMessageOprResponse extends BasePbResponse{
	
	protected MessageOpr mMessageOpr;
	protected MessageOpr.Builder mMessageOprBuilder;
	public BaseMessageOprResponse() {
		super();
		mMessageOprBuilder = MessageOpr.newBuilder();
	}
	
	@Override
	public void parseData() throws InvalidProtocolBufferException {
		// TODO Auto-generated method stub
		super.parseData();
		mMessageOpr = MessageOpr.parseFrom(mTransData
				.getBytesBody());
	}
	
	public PollMsgRes getPollMsgRes() throws InvalidProtocolBufferException
	{
		return PollMsgRes.parseFrom(mTransData
				.getBytesBody());
	}
	
	public PollUnreadInfoRes getPollUnreadMsgRes() throws InvalidProtocolBufferException
	{
		return PollUnreadInfoRes.parseFrom(mTransData
				.getBytesBody());
	}
}
