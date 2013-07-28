package com.heme.logic.httpprotocols.handleask;

import com.heme.logic.httpprotocols.base.BaseBusinessResponse;
import com.heme.logic.module.Data.ApplySocialGroupRsp;

public class HandleGroupAskResponse extends BaseBusinessResponse {
	ApplySocialGroupRsp mApplySocialGroupRsp;

	public ApplySocialGroupRsp getmGetGroupInfoRsp() {
		return mApplySocialGroupRsp;
	}

	public void parseData()
			throws com.google.protobuf.InvalidProtocolBufferException {
		mApplySocialGroupRsp = ApplySocialGroupRsp.parseFrom(mAccessRespData
				.getBytesBody());
	}
}
