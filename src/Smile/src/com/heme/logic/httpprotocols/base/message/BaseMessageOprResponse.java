package com.heme.logic.httpprotocols.base.message;

import com.google.protobuf.InvalidProtocolBufferException;
import com.heme.commonlogic.servermanager.BasePbResponse;
import com.heme.logic.module.Message.MessageOpr;

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
	
	public MessageOpr getmMessageOpr()
	{
		return mMessageOpr;
	}
}
