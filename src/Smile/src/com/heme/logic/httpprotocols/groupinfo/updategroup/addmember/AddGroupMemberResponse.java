package com.heme.logic.httpprotocols.groupinfo.updategroup.addmember;

import com.google.protobuf.InvalidProtocolBufferException;
import com.heme.logic.httpprotocols.base.BaseBusinessResponse;
import com.heme.logic.module.Data.AddGroupMemberRsp;

public class AddGroupMemberResponse extends BaseBusinessResponse {
	AddGroupMemberRsp mAddGroupMemberRsp;
	@Override
	public void parseData() throws InvalidProtocolBufferException {
		super.parseData();
		mAddGroupMemberRsp = AddGroupMemberRsp.parseFrom(mAccessRespData.getBytesBody());
	}
	
	public AddGroupMemberRsp getmCreateTempGroupRsp() {
		return mAddGroupMemberRsp;
	}
	
}
