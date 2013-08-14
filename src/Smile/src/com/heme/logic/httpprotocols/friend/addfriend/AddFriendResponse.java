package com.heme.logic.httpprotocols.friend.addfriend;

import com.google.protobuf.InvalidProtocolBufferException;
import com.heme.logic.httpprotocols.message.sendmsg.base.BaseMessageResponse;

public class AddFriendResponse extends BaseMessageResponse {
//	private FriendVerifyMsgRsp mAddfriendrsp;

	@Override
	public void parseData() throws InvalidProtocolBufferException {
		// TODO Auto-generated method stub
		super.parseData();
//		mAddfriendrsp = mDataSvrProto.getFr(mTransData
//				.getBytesBody());

	}

//	public FriendVerifyMsgRsp getFriendVerifyMsgRsp() {
//		return mAddfriendrsp;
//	}
}
