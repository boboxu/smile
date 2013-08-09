package com.heme.logic.httpprotocols.userinfo.updatesignature;

import com.heme.logic.httpprotocols.base.business.BaseBusinessResponse;
import com.heme.logic.module.Data.SetSignatureRsp;

public class UpdateSignatureResponse extends BaseBusinessResponse {
	SetSignatureRsp mSetSignatureRsp;
	
	@Override
	public void parseData() throws com.google.protobuf.InvalidProtocolBufferException 
	{
		super.parseData();
		mSetSignatureRsp = mDataSvrProto.getSetSignatureRspInfo();
	}
	
	public SetSignatureRsp getmSetSignatureRsp() {
		return mSetSignatureRsp;
	}
}
