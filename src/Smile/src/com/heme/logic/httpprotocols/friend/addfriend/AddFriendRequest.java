package com.heme.logic.httpprotocols.friend.addfriend;

import java.util.ArrayList;
import java.util.List;

import com.google.protobuf.ByteString;
import com.heme.logic.httpprotocols.message.sendmsg.base.BaseMessageRequest;
import com.heme.logic.module.Message.MessageType;
import com.heme.logic.module.Message.VerifyRequest;

public class AddFriendRequest extends BaseMessageRequest {
	
	public AddFriendRequest(long srcId, String sessionId,
			List<Long> targetIdList) {
		super(srcId, sessionId, targetIdList, new ArrayList<Long>(), MessageType.MT_VerifyReq);
	}

	public void setVerifyRequest(VerifyRequest verifyMsgInfo,ByteString context)
	{
		mCommonMsgBuilder.setMsgVerifyReq(verifyMsgInfo);
		super.setCommonMsg(context);
	}
}
