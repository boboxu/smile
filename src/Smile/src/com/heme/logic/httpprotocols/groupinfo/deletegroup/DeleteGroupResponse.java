package com.heme.logic.httpprotocols.groupinfo.deletegroup;

import com.google.protobuf.InvalidProtocolBufferException;
import com.heme.logic.httpprotocols.base.business.BaseBusinessResponse;
import com.heme.logic.module.Data.DelGroupRsp;

public class DeleteGroupResponse extends BaseBusinessResponse {
	private DelGroupRsp mDelGroupRsp;
	public DelGroupRsp getmDelGroupRsp() {
		return mDelGroupRsp;
	}
	
	@Override
	public void parseData() throws InvalidProtocolBufferException {
		super.parseData();
		mDelGroupRsp = mDataSvrProto.getDelGroupRspInfo();
	}
}
