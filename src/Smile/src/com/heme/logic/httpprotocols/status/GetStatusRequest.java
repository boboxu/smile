package com.heme.logic.httpprotocols.status;

import java.util.List;

import com.heme.logic.httpprotocols.base.status.BaseStatusRequest;
import com.heme.logic.module.Status.GetStatusReq;
import com.heme.logic.module.Status.StatusProto.Cmd;

public class GetStatusRequest extends BaseStatusRequest {
	
	private GetStatusReq.Builder mGetStatusBuilder;

	public void setTargetIdList(List<Long> targetIdList) {
		mGetStatusBuilder.addAllRptUid(targetIdList);
		mStatusProtoBuilder.setEnumCmd(Cmd.GetStatus);
		mStatusProtoBuilder.setGetStatusReq(mGetStatusBuilder.build());
		super.setBody(mStatusProtoBuilder.build().toByteString());
	}

	@Override
	public void initmDataBuilder() {
		mGetStatusBuilder = GetStatusReq.newBuilder();
	}
}
