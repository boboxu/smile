package com.heme.logic.httpprotocols.groupinfo.creategroup.temp;

import com.google.protobuf.InvalidProtocolBufferException;
import com.heme.logic.httpprotocols.base.BaseBusinessResponse;
import com.heme.logic.module.Data.CreateTempGroupRsp;

public class CreateTempGroupResponse extends BaseBusinessResponse {
	private CreateTempGroupRsp mCreateTempGroupRsp;
	public CreateTempGroupRsp getmCreateTempGroupRsp() {
		return mCreateTempGroupRsp;
	}
	
	@Override
	public void parseData() throws InvalidProtocolBufferException {
		super.parseData();
		mCreateTempGroupRsp = CreateTempGroupRsp.parseFrom(mAccessRespData.getBytesBody());
	}
	
	
	
}
