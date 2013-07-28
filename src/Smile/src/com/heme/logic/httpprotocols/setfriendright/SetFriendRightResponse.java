package com.heme.logic.httpprotocols.setfriendright;

import com.heme.logic.httpprotocols.base.BaseBusinessResponse;
import com.heme.logic.module.Data.SetFriendRightRsp;

public class SetFriendRightResponse extends BaseBusinessResponse {
	SetFriendRightRsp mSetFriendRightRsp;

	public SetFriendRightRsp getmGetGroupInfoRsp() {
		return mSetFriendRightRsp;
	}

	public void parseData()
			throws com.google.protobuf.InvalidProtocolBufferException {
		mSetFriendRightRsp = SetFriendRightRsp.parseFrom(mAccessRespData
				.getBytesBody());
	}
}
