package com.heme.logic.httpprotocols.friend.delfriend;

import com.google.protobuf.InvalidProtocolBufferException;
import com.heme.logic.httpprotocols.base.BaseBusinessResponse;
import com.heme.logic.module.Data.DelFriendRsp;

public class DelFriendResponse extends BaseBusinessResponse {
	private DelFriendRsp mDelFriendRsp;

	public DelFriendRsp getmDelFriendRsp() {
		return mDelFriendRsp;
	}

	public void parseData() throws InvalidProtocolBufferException {
		super.parseData();
		mDelFriendRsp = DelFriendRsp.parseFrom(mTransData.getBytesBody());
	}

}
