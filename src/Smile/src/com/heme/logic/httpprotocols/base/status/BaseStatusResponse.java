package com.heme.logic.httpprotocols.base.status;

import com.google.protobuf.InvalidProtocolBufferException;
import com.heme.commonlogic.servermanager.BasePbResponse;
import com.heme.logic.module.Status.StatusProto;

public class BaseStatusResponse extends BasePbResponse {
	protected StatusProto mStatusProto;
	@Override
	public void parseData() throws InvalidProtocolBufferException {
		// TODO Auto-generated method stub
		super.parseData();
		mStatusProto = StatusProto.parseFrom(mTransData.getBytesBody());
	}
}
