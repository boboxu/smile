package com.heme.logic.httpprotocols.heartbeat;

import com.heme.logic.httpprotocols.base.status.BaseStatusResponse;
import com.heme.logic.module.Status.HeartbeatRsp;

public class HeartBeatResponse extends BaseStatusResponse {

	HeartbeatRsp mHeartbeatRsp;
	public HeartbeatRsp getmHeartbeatRsp() {
		return mHeartbeatRsp;
	}
	public void parseData()
			throws com.google.protobuf.InvalidProtocolBufferException {
		super.parseData();
		mHeartbeatRsp = mStatusProto.getHeartBeatRsp();
	}
}
