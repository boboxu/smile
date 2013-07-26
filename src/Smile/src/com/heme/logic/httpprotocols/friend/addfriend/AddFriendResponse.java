package com.heme.logic.httpprotocols.friend.addfriend;

import com.google.protobuf.InvalidProtocolBufferException;
import com.heme.logic.httpprotocols.base.BaseBusinessResponse;
import com.heme.logic.module.Data.FriendVerifyMsgRsp;

public class AddFriendResponse extends BaseBusinessResponse {
	private FriendVerifyMsgRsp mAddfriendrsp;
	
	@Override
	public void parseData() {
		// TODO Auto-generated method stub
		super.parseData();
		try {
			mAddfriendrsp = FriendVerifyMsgRsp.parseFrom(mAccessRespData.getBytesBody());
		} catch (InvalidProtocolBufferException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public FriendVerifyMsgRsp getFriendVerifyMsgRsp()
	{
		return mAddfriendrsp;
	}
}
