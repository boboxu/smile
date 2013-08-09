package com.heme.logic.httpprotocols.heartbeat;

import com.heme.logic.httpprotocols.base.status.BaseStatusRequest;
import com.heme.logic.module.Status.HeartbeatReq;
import com.heme.logic.module.Status.StatusProto.Cmd;

public class HeartBeatRequest extends BaseStatusRequest {

	HeartbeatReq.Builder mHeartbeatReqBuilder;

	@Override
	public void initmDataBuilder() {
		mHeartbeatReqBuilder = HeartbeatReq.newBuilder();
	}

	public HeartBeatRequest(long systemId) {
		mHeartbeatReqBuilder.setUint64Uid(systemId);
		mStatusProtoBuilder.setEnumCmd(Cmd.Heartbeat);
		mStatusProtoBuilder.setHeartBeatReq(mHeartbeatReqBuilder.build());
		super.setBody(mStatusProtoBuilder.build().toByteString());
	}
}
