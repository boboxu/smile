package com.heme.logic.httpprotocols.message.sendmsg.c2g;

import java.util.ArrayList;
import java.util.List;

import com.heme.logic.httpprotocols.message.sendmsg.base.BaseMessageRequest;
import com.heme.logic.module.Message.MessageType;

public class SendGroupMsgRequest extends BaseMessageRequest {

	public SendGroupMsgRequest(long srcId, String sessionId,
			List<Long> mTargetGid) {
		super(srcId, sessionId, new ArrayList<Long>(), mTargetGid, MessageType.MT_Group);
	}

}
