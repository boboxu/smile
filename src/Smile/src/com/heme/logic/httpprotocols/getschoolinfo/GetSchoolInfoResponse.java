package com.heme.logic.httpprotocols.getschoolinfo;

import com.google.protobuf.InvalidProtocolBufferException;
import com.heme.logic.httpprotocols.base.BaseBusinessResponse;
import com.heme.logic.module.Data.RegGetSchoolRsp;

public class GetSchoolInfoResponse extends BaseBusinessResponse {
	RegGetSchoolRsp mRegGetClassRsp;

	public RegGetSchoolRsp getmRegGetSchoolRsp() {
		return mRegGetClassRsp;
	}
	
	@Override
	public void parseData() throws InvalidProtocolBufferException {
		super.parseData();
		mRegGetClassRsp = RegGetSchoolRsp.parseFrom(mTransData.getBytesBody());
	}
	
	
}
