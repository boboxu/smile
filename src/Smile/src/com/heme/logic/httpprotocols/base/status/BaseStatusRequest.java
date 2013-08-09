package com.heme.logic.httpprotocols.base.status;

import com.heme.commonlogic.servermanager.BasePbRequest;
import com.heme.logic.module.Status.StatusProto;

public abstract class BaseStatusRequest extends BasePbRequest {

	protected StatusProto.Builder mStatusProtoBuilder;
	@Override
	protected void setCmd() {
		mTransDataBuilder.setStrCmd("StatusSvr");
	}

	public BaseStatusRequest() {
		super();
		mStatusProtoBuilder = StatusProto.newBuilder();
		initmDataBuilder();
	}

	public abstract void initmDataBuilder();
}
