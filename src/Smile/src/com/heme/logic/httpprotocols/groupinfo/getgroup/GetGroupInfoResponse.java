package com.heme.logic.httpprotocols.groupinfo.getgroup;

import com.heme.logic.httpprotocols.base.BaseBusinessResponse;
import com.heme.logic.module.Data.GetGroupInfoRsp;

public class GetGroupInfoResponse extends BaseBusinessResponse {
	GetGroupInfoRsp mGetGroupInfoRsp;

	public GetGroupInfoRsp getmGetGroupInfoRsp() {
		return mGetGroupInfoRsp;
	}

	public void parseData()
			throws com.google.protobuf.InvalidProtocolBufferException {
		mGetGroupInfoRsp = GetGroupInfoRsp.parseFrom(mAccessRespData
				.getBytesBody());
	}
	
	
}
