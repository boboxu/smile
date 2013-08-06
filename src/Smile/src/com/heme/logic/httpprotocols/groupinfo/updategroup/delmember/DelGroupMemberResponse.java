package com.heme.logic.httpprotocols.groupinfo.updategroup.delmember;

import com.google.protobuf.InvalidProtocolBufferException;
import com.heme.logic.httpprotocols.base.BaseBusinessResponse;
import com.heme.logic.module.Data.DelGroupMemberRsp;

public class DelGroupMemberResponse extends BaseBusinessResponse {
	DelGroupMemberRsp mDelGroupMemberRsp;
	@Override
	public void parseData() throws InvalidProtocolBufferException {
		super.parseData();
		mDelGroupMemberRsp = DelGroupMemberRsp.parseFrom(mTransData.getBytesBody());
	}
	
	public DelGroupMemberRsp getmDelTempGroupRsp() {
		return mDelGroupMemberRsp;
	}
}
