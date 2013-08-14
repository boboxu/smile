package com.heme.logic.httpprotocols.message.sendmsg.c2c;

import java.util.ArrayList;
import java.util.List;

import com.heme.logic.httpprotocols.message.sendmsg.base.BaseMessageRequest;
import com.heme.logic.module.Message.MessageType;

public class SendUserMsgRequest extends BaseMessageRequest {

	public SendUserMsgRequest(long srcId, String sessionId,
			List<Long> targetIdList) {
		super(srcId, sessionId, targetIdList, new ArrayList<Long>(), MessageType.MT_C2C);
	}

}
