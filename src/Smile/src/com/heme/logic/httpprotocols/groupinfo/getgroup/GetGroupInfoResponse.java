package com.heme.logic.httpprotocols.groupinfo.getgroup;

import com.heme.logic.httpprotocols.base.business.BaseBusinessResponse;
import com.heme.logic.module.Data.GetGroupInfoRsp;

public class GetGroupInfoResponse extends BaseBusinessResponse {
	GetGroupInfoRsp mGetGroupInfoRsp;

	public GetGroupInfoRsp getmGetGroupInfoRsp() {
		return mGetGroupInfoRsp;
	}

	public void parseData()
			throws com.google.protobuf.InvalidProtocolBufferException {
		super.parseData();
		mGetGroupInfoRsp = mDataSvrProto.getGetGroupInfoRspInfo();
	}
	
	
}
