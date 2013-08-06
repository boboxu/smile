package com.heme.logic.httpprotocols.friend.updatefriend;

import com.google.protobuf.InvalidProtocolBufferException;
import com.heme.logic.httpprotocols.base.BaseBusinessResponse;
import com.heme.logic.module.Data.SetFriendDescRsp;

public class UpdateFriendResponse extends BaseBusinessResponse {
	private SetFriendDescRsp mSetFriendDescRsp;
	
	@Override
	public void parseData() throws InvalidProtocolBufferException {
		// TODO Auto-generated method stub
		super.parseData();
		mSetFriendDescRsp = SetFriendDescRsp.parseFrom(mTransData.getBytesBody());
	}

	public SetFriendDescRsp getmSetFriendDescRsp() {
		return mSetFriendDescRsp;
	}
	
	
}
