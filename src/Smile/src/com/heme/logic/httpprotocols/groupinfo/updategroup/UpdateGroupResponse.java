package com.heme.logic.httpprotocols.groupinfo.updategroup;

import com.google.protobuf.InvalidProtocolBufferException;
import com.heme.logic.httpprotocols.base.BaseBusinessResponse;
import com.heme.logic.module.Data.SetGroupNameRsp;

public class UpdateGroupResponse extends BaseBusinessResponse {
	private SetGroupNameRsp mSetGroupNameRsp;
	public SetGroupNameRsp getmSetGroupNameRsp() {
		return mSetGroupNameRsp;
	}
	
	@Override
	public void parseData() throws InvalidProtocolBufferException {
		super.parseData();
		mSetGroupNameRsp = SetGroupNameRsp.parseFrom(mTransData.getBytesBody());
	}
}
