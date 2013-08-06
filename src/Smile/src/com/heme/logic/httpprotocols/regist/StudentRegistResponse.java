package com.heme.logic.httpprotocols.regist;

import com.google.protobuf.InvalidProtocolBufferException;
import com.heme.logic.httpprotocols.base.BaseBusinessResponse;
import com.heme.logic.module.Data.RegStudentRsp;

public class StudentRegistResponse extends BaseBusinessResponse {
	RegStudentRsp mRegStudentRsp;
	@Override
	public void parseData() throws InvalidProtocolBufferException {
		super.parseData();
		mRegStudentRsp = RegStudentRsp.parseFrom(mTransData.getBytesBody());
	}
	
	public RegStudentRsp getmRegStudentRsp() {
		return mRegStudentRsp;
	}
}
