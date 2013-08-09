package com.heme.logic.httpprotocols.friend.addfriend;

import com.google.protobuf.InvalidProtocolBufferException;
import com.heme.logic.httpprotocols.base.business.BaseBusinessResponse;
import com.heme.logic.module.Data.FriendVerifyMsgRsp;

public class AddFriendResponse extends BaseBusinessResponse {
	private FriendVerifyMsgRsp mAddfriendrsp;

	@Override
	public void parseData() throws InvalidProtocolBufferException {
		// TODO Auto-generated method stub
		super.parseData();
//		mAddfriendrsp = mDataSvrProto.getFr(mTransData
//				.getBytesBody());

	}

	public FriendVerifyMsgRsp getFriendVerifyMsgRsp() {
		return mAddfriendrsp;
	}
}
