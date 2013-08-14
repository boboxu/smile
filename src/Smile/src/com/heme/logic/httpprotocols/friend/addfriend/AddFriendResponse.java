package com.heme.logic.httpprotocols.friend.addfriend;

import com.heme.logic.httpprotocols.base.business.BaseBusinessResponse;
import com.heme.logic.module.Data.AddFriendRsp;

public class AddFriendResponse extends BaseBusinessResponse {
	AddFriendRsp mAddFriendRsp;
	public AddFriendRsp getmAddFriendRsp() {
		return mAddFriendRsp;
	}
	
	public void parseData() throws com.google.protobuf.InvalidProtocolBufferException {
		super.parseData();
		mAddFriendRsp = mDataSvrProto.getAddFriendRspInfo();
	}
}
