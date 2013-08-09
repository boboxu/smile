package com.heme.logic.httpprotocols.getschoolinfo;

import com.heme.logic.httpprotocols.base.business.BaseBusinessResponse;
import com.heme.logic.module.Data.RegGetClassRsp;

public class GetClassInfoResponse extends BaseBusinessResponse {
	
	private RegGetClassRsp mRegGetClassRsp;

	public RegGetClassRsp getmRegGetClassRsp() {
		return mRegGetClassRsp;
	}
	
	public void parseData() throws com.google.protobuf.InvalidProtocolBufferException 
	{
		super.parseData();
		mRegGetClassRsp = mDataSvrProto.getRegGetClassRspInfo();
	}
}
