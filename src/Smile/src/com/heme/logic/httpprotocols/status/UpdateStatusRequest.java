package com.heme.logic.httpprotocols.status;

import com.heme.logic.httpprotocols.base.status.BaseStatusRequest;
import com.heme.logic.module.Status.EStatus;
import com.heme.logic.module.Status.SetStatusReq;
import com.heme.logic.module.Status.StatusProto.Cmd;

public class UpdateStatusRequest extends BaseStatusRequest {
	
	private SetStatusReq.Builder mSetStatusBuilder;

	public void setStatus(EStatus status) {
		mSetStatusBuilder.setEnumStatus(status);
		mStatusProtoBuilder.setEnumCmd(Cmd.SetStatus);
		mStatusProtoBuilder.setSetStatusReq(mSetStatusBuilder.build());
		super.setBody(mStatusProtoBuilder.build().toByteString());
	}

	@Override
	public void initmDataBuilder() {
		mSetStatusBuilder = SetStatusReq.newBuilder();
	}
}
