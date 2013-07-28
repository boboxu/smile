package com.heme.logic.httpprotocols.regist;

import com.google.protobuf.InvalidProtocolBufferException;
import com.heme.logic.httpprotocols.base.BaseBusinessResponse;
import com.heme.logic.module.Data.RegParentRsp;

public class ParentRegistResponse extends BaseBusinessResponse {
	RegParentRsp mRegParentRsp;
	@Override
	public void parseData() throws InvalidProtocolBufferException {
		super.parseData();
		mRegParentRsp = RegParentRsp.parseFrom(mAccessRespData.getBytesBody());
	}
	
	public RegParentRsp getmRegParentRsp() {
		return mRegParentRsp;
	}
}
