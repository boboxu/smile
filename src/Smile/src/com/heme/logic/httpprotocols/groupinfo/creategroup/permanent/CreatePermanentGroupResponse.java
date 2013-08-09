package com.heme.logic.httpprotocols.groupinfo.creategroup.permanent;

import com.google.protobuf.InvalidProtocolBufferException;
import com.heme.logic.httpprotocols.base.business.BaseBusinessResponse;
import com.heme.logic.module.Data.CreateFixedGroupRsp;

public class CreatePermanentGroupResponse extends BaseBusinessResponse {
	CreateFixedGroupRsp mCreateFixedGroupRsp;
	public CreateFixedGroupRsp getmCreateFixedGroupRsp() {
		return mCreateFixedGroupRsp;
	}
	
	@Override
	public void parseData() throws InvalidProtocolBufferException {
		super.parseData();
		mCreateFixedGroupRsp = mDataSvrProto.getCreateFixedGroupRspInfo();
	}
	
	
}
