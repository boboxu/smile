package com.heme.commonlogic.servermanager;

import com.google.protobuf.InvalidProtocolBufferException;
import com.heme.logic.module.Trans.TransProto;

public class BasePbResponse extends BaseResponse {
	protected TransProto mTransData;
	protected TransProto.Builder mTransDataBuilder;
	
	public BasePbResponse()
	{
		mTransDataBuilder = TransProto.newBuilder();
	}
	
	@Override
	public void parseData() throws InvalidProtocolBufferException
	{
		super.parseData();
		mTransData = TransProto.parseFrom(mRespData);
	}
	
	
}