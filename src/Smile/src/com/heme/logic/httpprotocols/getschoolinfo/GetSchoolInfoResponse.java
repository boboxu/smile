package com.heme.logic.httpprotocols.getschoolinfo;

import com.google.protobuf.InvalidProtocolBufferException;
import com.heme.logic.httpprotocols.base.BaseBusinessResponse;
import com.heme.logic.module.Data.RegGetClassRsp;

public class GetSchoolInfoResponse extends BaseBusinessResponse {
	RegGetClassRsp mRegGetClassRsp;

	public RegGetClassRsp getmRegGetClassRsp() {
		return mRegGetClassRsp;
	}
	
	@Override
	public void parseData() throws InvalidProtocolBufferException {
		super.parseData();
		mRegGetClassRsp = RegGetClassRsp.parseFrom(mAccessRespData.getBytesBody());
	}
	
	
}
