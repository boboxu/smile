package com.heme.logic.httpprotocols.groupinfo.updategroup.addmember;

import com.google.protobuf.InvalidProtocolBufferException;
import com.heme.logic.httpprotocols.base.business.BaseBusinessResponse;
import com.heme.logic.module.Data.AddGroupMemberRsp;

public class AddGroupMemberResponse extends BaseBusinessResponse {
	AddGroupMemberRsp mAddGroupMemberRsp;
	@Override
	public void parseData() throws InvalidProtocolBufferException {
		super.parseData();
		mAddGroupMemberRsp = mDataSvrProto.getAddGroupMemberRspInfo();
	}
	
	public AddGroupMemberRsp getmAddTempGroupRsp() {
		return mAddGroupMemberRsp;
	}
	
}
