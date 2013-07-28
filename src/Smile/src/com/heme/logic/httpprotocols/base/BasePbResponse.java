package com.heme.logic.httpprotocols.base;

import com.google.protobuf.InvalidProtocolBufferException;
import com.heme.commonlogic.servermanager.BaseResponse;
import com.heme.logic.module.Access.AccessResp;

public class BasePbResponse extends BaseResponse {
	protected AccessResp mAccessRespData;
	@Override
	public void parseData() throws InvalidProtocolBufferException
	{
		super.parseData();
		mAccessRespData = AccessResp.parseFrom(mRespData);
	}
}
