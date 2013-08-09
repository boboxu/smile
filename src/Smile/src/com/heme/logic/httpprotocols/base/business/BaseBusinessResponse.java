package com.heme.logic.httpprotocols.base.business;

import com.google.protobuf.InvalidProtocolBufferException;
import com.heme.commonlogic.servermanager.BasePbResponse;
import com.heme.logic.module.Data.DataSvrProto;


public class BaseBusinessResponse extends BasePbResponse{
	protected DataSvrProto mDataSvrProto = null;
	@Override
	public void parseData() throws InvalidProtocolBufferException {
		super.parseData();
		mDataSvrProto = DataSvrProto.parseFrom(mTransData.getBytesBody());
	}
}
