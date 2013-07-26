package com.heme.logic.httpprotocols.base;

import com.google.protobuf.InvalidProtocolBufferException;
import com.heme.commonlogic.servermanager.BaseResponse;
import com.heme.logic.module.Access.AccessResp;

public class BasePbResponse extends BaseResponse {
	protected AccessResp mAccessRespData;
	@Override
	public void parseData() 
	{
		super.parseData();
		try {
			mAccessRespData = AccessResp.parseFrom(mDataBuffer.array());
		} catch (InvalidProtocolBufferException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
