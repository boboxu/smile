package com.heme.logic.httpprotocols.heartbeat;

import com.heme.logic.httpprotocols.base.BaseBusinessResponse;
import com.heme.logic.module.Data.HbRsp;

public class HeartBeatResponse extends BaseBusinessResponse {
	HbRsp mHbRsp;

	public HbRsp getmGetGroupInfoRsp() {
		return mHbRsp;
	}

	public void parseData()
			throws com.google.protobuf.InvalidProtocolBufferException {
		mHbRsp = HbRsp.parseFrom(mAccessRespData
				.getBytesBody());
	}
}
