package com.heme.logic.httpprotocols.message.sendmsg.c2c;

import java.util.ArrayList;
import java.util.List;

import com.heme.logic.httpprotocols.message.sendmsg.base.BaseMessageRequest;

public class SendUserMsgRequest extends BaseMessageRequest {

	protected SendUserMsgRequest(long srcId, int sessionId,
			List<Long> mTargetId,
			CONTENTTYPE contentType) {
		super(srcId, sessionId, mTargetId, new ArrayList<Long>(), MSGTYPE.TYPEC2C);
	}

}
