package com.heme.logic.httpprotocols.message.sendmsg.c2c;

import java.util.ArrayList;
import java.util.List;

import com.heme.logic.httpprotocols.message.sendmsg.base.BaseMessageRequest;

public class SendUserMsgRequest extends BaseMessageRequest {

	public SendUserMsgRequest(long srcId, String sessionId,
			List<Long> mTargetId) {
		super(srcId, sessionId, mTargetId, new ArrayList<Long>(), MSGTYPE.TYPEC2C);
	}

}
